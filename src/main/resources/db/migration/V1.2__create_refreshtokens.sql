CREATE TABLE refreshtokens (
    token_id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL UNIQUE,
    member_id BIGINT NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_refreshtokens_member FOREIGN KEY (member_id)
        REFERENCES members(member_id)
);
