CREATE TABLE members (
    member_id BIGSERIAL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(15) NOT NULL UNIQUE,
    role VARCHAR(10) NOT NULL,
    sns VARCHAR(6),
    sns_id BIGINT
);

ALTER TABLE members
ADD CONSTRAINT role_check CHECK (role IN ('ROLE_USER', 'ROLE_ADMIN'));

ALTER TABLE members
ADD CONSTRAINT sns_check CHECK (sns IN ('KAKAO', 'NAVER', 'GOOGLE'));