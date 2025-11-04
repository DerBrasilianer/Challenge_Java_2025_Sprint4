-- V5
-- Inserir Pátio
IF NOT EXISTS (SELECT 1 FROM CH_TB_PATIO WHERE nome = 'Pátio Flyway')
BEGIN
  INSERT INTO CH_TB_PATIO (nome, endereco, telefone, observacao)
  VALUES ('Pátio Flyway', 'Av. Flyway, 123', 119999999, 'Criado via Flyway');
END;

-- Inserir Usuário (nome: professor, senha: professorpass)
IF NOT EXISTS (SELECT 1 FROM CH_TB_USER WHERE username = 'professor')
BEGIN
  INSERT INTO CH_TB_USER (username, password, role, email)
  VALUES ('professor', '$2a$10$NaNyN9d1nGA/Zfowh12ouORXUWTACVCzbFU0vz/kajRRV2dn49cGa', 'ADMIN', 'calma@tranquilo.com');
END;

-- Inserir Moto
IF NOT EXISTS (SELECT 1 FROM CH_TB_MOTO WHERE placa = 'FLY-2025')
BEGIN
  INSERT INTO CH_TB_MOTO (marca, modelo, placa, ano, numero_iot, patio_id, data_fabricacao)
  VALUES (
    'HONDA',
    'POP',
    'FLY-2025',
    2024,
    123456789,
    (SELECT id FROM CH_TB_PATIO WHERE nome = 'Pátio Flyway'),
    '2024-01-01'
  );
END;
