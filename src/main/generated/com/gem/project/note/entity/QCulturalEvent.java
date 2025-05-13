package com.gem.project.note.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCulturalEvent is a Querydsl query type for CulturalEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCulturalEvent extends EntityPathBase<CulturalEvent> {

    private static final long serialVersionUID = 17704232L;

    public static final QCulturalEvent culturalEvent = new QCulturalEvent("culturalEvent");

    public final StringPath additionalInfo = createString("additionalInfo");

    public final StringPath admissionFee = createString("admissionFee");

    public final StringPath applicantType = createString("applicantType");

    public final DateTimePath<java.time.LocalDateTime> applicationDate = createDateTime("applicationDate", java.time.LocalDateTime.class);

    public final StringPath category = createString("category");

    public final StringPath district = createString("district");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final StringPath eventName = createString("eventName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath isFree = createString("isFree");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath organizationName = createString("organizationName");

    public final StringPath performerInfo = createString("performerInfo");

    public final StringPath portalDetailUrl = createString("portalDetailUrl");

    public final StringPath programDescription = createString("programDescription");

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath targetAudience = createString("targetAudience");

    public final StringPath themeCategory = createString("themeCategory");

    public final StringPath thumbnailUrl = createString("thumbnailUrl");

    public final StringPath venue = createString("venue");

    public final StringPath websiteUrl = createString("websiteUrl");

    public QCulturalEvent(String variable) {
        super(CulturalEvent.class, forVariable(variable));
    }

    public QCulturalEvent(Path<? extends CulturalEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCulturalEvent(PathMetadata metadata) {
        super(CulturalEvent.class, metadata);
    }

}

