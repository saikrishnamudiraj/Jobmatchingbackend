package org.example.jobmatch.user.service;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.example.jobmatch.user.dto.request.UserSearchRequest;
import org.example.jobmatch.user.dto.response.UserSummaryDto;
import org.example.jobmatch.user.entity.UserProfile;
import org.example.jobmatch.user.repository.UserProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserQueryService {

    private final UserProfileRepository repository;

    public UserQueryService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public Page<UserSummaryDto> search(UserSearchRequest req, Pageable pageable) {
        Specification<UserProfile> spec = buildSpecification(req);
        Page<UserProfile> result = repository.findAll(spec, pageable);
        return result.map(this::toDto);
    }

    private Specification<UserProfile> buildSpecification(UserSearchRequest req) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

            if (req == null) return cb.and(predicates.toArray(new Predicate[0]));

            // free text filter
            if (req.getQ() != null && !req.getQ().isBlank()) {
                String like = "%" + req.getQ().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("firstName")), like),
                        cb.like(cb.lower(root.get("lastName")), like),
                        cb.like(cb.lower(root.get("profileSummary")), like)
                ));
            }

            // experience filter
            if (req.getMinExperience() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("experienceYears"), req.getMinExperience()));
            }
            if (req.getMaxExperience() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("experienceYears"), req.getMaxExperience()));
            }

            // salary filter
            if (req.getMinSalary() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), req.getMinSalary()));
            }
            if (req.getMaxSalary() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("salary"), req.getMaxSalary()));
            }

            // helper for list filters
            addCollectionFilter(root.join("skills", JoinType.LEFT), req.getSkills(), predicates);
            addCollectionFilter(root.join("languages", JoinType.LEFT), req.getLanguages(), predicates);
            addCollectionFilter(root.join("companies", JoinType.LEFT), req.getCompanies(), predicates);
            addCollectionFilter(root.join("educations", JoinType.LEFT), req.getEducations(), predicates);
            addCollectionFilter(root.join("certifications", JoinType.LEFT), req.getCertifications(), predicates);
            addCollectionFilter(root.join("interests", JoinType.LEFT), req.getInterests(), predicates);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void addCollectionFilter(Join<Object, Object> join, List<String> values, List<Predicate> predicates) {
        if (!CollectionUtils.isEmpty(values)) {
            List<String> clean = values.stream()
                    .filter(Objects::nonNull)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
            if (!clean.isEmpty()) {
                predicates.add(join.in(clean));
            }
        }
    }

    private UserSummaryDto toDto(UserProfile entity) {
        return UserSummaryDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .profileSummary(entity.getProfileSummary())
                .experienceYears(entity.getExperienceYears())
                .salary(entity.getSalary())
                .profilePicture(entity.getProfilePicture())
                .skills(entity.getSkills())
                .languages(entity.getLanguages())
                .companies(entity.getCompanies())
                .build();
    }
}