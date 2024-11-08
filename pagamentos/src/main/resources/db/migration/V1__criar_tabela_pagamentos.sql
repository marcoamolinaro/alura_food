CREATE TABLE pagamentos (
    id bigint NOT NULL AUTO_INCREMENT,
    valor decimal(19,2) NOT NULL,
    nome varchar(100) NOT NULL,
    numero varchar(19) DEFAULT NULL,
    expiracao varchar(7) NOT NULL,
    codigo VARCHAR(3) DEFAULT NULL,
    status varchar(255) NOT NULL,
    forma_de_pagamento bigint NOT NULL,
    pedido_id bigint NOT NULL,
  PRIMARY KEY (id)
);