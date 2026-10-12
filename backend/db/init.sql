CREATE TABLE IF NOT EXISTS sqli_incidents (
    message_id VARCHAR(128) PRIMARY KEY,
    vulnerability_type VARCHAR(32),
    source_ip VARCHAR(45),
    target_endpoint TEXT,
    payload TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS xss_incidents (
    message_id VARCHAR(128) PRIMARY KEY,
    vulnerability_type VARCHAR(32),
    source_ip VARCHAR(45),
    target_endpoint TEXT,
    payload TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);