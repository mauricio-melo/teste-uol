CREATE TABLE client (
  idt_client                    BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  nam_client	                VARCHAR(255) NOT NULL,
  age_client                    BIGINT       NULL,
  flg_enabled                   BIT          NOT NULL,
  dat_creation                  DATETIME     NOT NULL,
  dat_update                    DATETIME     NULL
);