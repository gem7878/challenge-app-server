package com.gem.project.note.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cultural_events")
public class CulturalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String district;

    @Column(name = "event_name")
    private String eventName;

    private String venue;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "admission_fee")
    private String admissionFee;

    @Column(name = "performer_info")
    private String performerInfo;

    @Column(name = "program_description", columnDefinition = "TEXT")
    private String programDescription;

    @Column(name = "additional_info", columnDefinition = "TEXT")
    private String additionalInfo;

    @Column(name = "website_url", columnDefinition = "TEXT")
    private String websiteUrl;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    @Column(name = "applicant_type")
    private String applicantType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "theme_category")
    private String themeCategory;

    private Double latitude;

    private Double longitude;

    @Column(name = "is_free")
    private String isFree;

    @Column(name = "portal_detail_url", columnDefinition = "TEXT")
    private String portalDetailUrl;
}
