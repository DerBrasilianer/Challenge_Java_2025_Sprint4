-- V5
-- Inserir Pátio
MERGE INTO CH_TB_PATIO p
USING (SELECT 'Pátio Flyway' AS nome FROM dual) src
ON (p.nome = src.nome)
WHEN NOT MATCHED THEN
  INSERT (nome, endereco, telefone, observacao)
  VALUES ('Pátio Flyway', 'Av. Flyway, 123', 119999999, 'Criado via Flyway');

-- Inserir Usuário (nome: professor, senha: professorpass)
MERGE INTO CH_TB_USER u
USING (SELECT 'professor' AS username FROM dual) src
ON (u.username = src.username)
WHEN NOT MATCHED THEN
  INSERT (username, password, role, email)
  VALUES ('professor', '$2a$10$NaNyN9d1nGA/Zfowh12ouORXUWTACVCzbFU0vz/kajRRV2dn49cGa', 'ADMIN', 'calma@tranquilo.com');

-- Inserir Moto
MERGE INTO CH_TB_MOTO m
USING (SELECT 'FLY-2025' AS placa FROM dual) src
ON (m.placa = src.placa)
WHEN NOT MATCHED THEN
  INSERT (marca, modelo, placa, ano, numero_iot, patio_id, data_fabricacao)
  VALUES ('HONDA', 'POP', 'FLY-2025', 2024, 123456789,
          (SELECT id FROM CH_TB_PATIO WHERE nome = 'Pátio Flyway'),
          TO_DATE('2024-01-01','YYYY-MM-DD'));

