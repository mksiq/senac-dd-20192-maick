drop database if exists dbmaickelAtividade1;

CREATE SCHEMA dbmaickelAtividade1;

use dbmaickelAtividade1;


-- int id, String nome, Lotacao lotacaoSuperior, Funcionario responsavel
CREATE TABLE LOTACAO(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100), 
	sigla VARCHAR(3),
	idlotacao_superior INT,
	idfuncionario_responsavel INT,
	FOREIGN KEY (idlotacao_superior) REFERENCES lotacao(id),
	FOREIGN KEY (idfuncionario_responsavel) REFERENCES funcionario(id)
);


CREATE TABLE FUNCIONARIO(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100),
	cpf VARCHAR(11),
	sexo CHAR,
	idade INT,
	salariobruto DOUBLE,
	comissao DOUBLE,
	descontoImpostoRenda DOUBLE,
	descontoPrevidencia DOUBLE,
	salarioBase DOUBLE,
	idlotacao INT,
	cargo VARCHAR(20), 
	FOREIGN KEY (idlotacao) REFERENCES lotacao(id)
);

	select * from lotacao;
	select * from funcionario;
