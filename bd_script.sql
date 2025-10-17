create database maintenance_system2;
use maintenance_system2;

-- Fornecedores
CREATE TABLE Fornecedor (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
cnpj VARCHAR(14) UNIQUE NOT NULL
);
select * from Fornecedor;

-- Materiais
CREATE TABLE Material (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
unidade VARCHAR(20) NOT NULL,
estoque DOUBLE NOT NULL
);
select * from Material;

-- Requisições de Material
CREATE TABLE Requisicao (
id INT PRIMARY KEY AUTO_INCREMENT,
setor VARCHAR(50) NOT NULL,
dataSolicitacao DATE NOT NULL DEFAULT (CURRENT_DATE),
status ENUM('PENDENTE', 'ATENDIDA', 'CANCELADA') DEFAULT 'PENDENTE'
);
select * from Requisicao;

-- Itens da Requisição
CREATE TABLE RequisicaoItem (
idRequisicao INT NOT NULL,
idMaterial INT NOT NULL,
quantidade DOUBLE NOT NULL,
FOREIGN KEY (idRequisicao) REFERENCES Requisicao(id),
FOREIGN KEY (idMaterial) REFERENCES Material(id)
);
select * from RequisicaoItem;

-- Notas de Entrada (compras recebidas de fornecedores)
CREATE TABLE NotaEntrada (
id INT PRIMARY KEY AUTO_INCREMENT,
idFornecedor INT NOT NULL,
dataEntrada DATE NOT NULL DEFAULT (CURRENT_DATE),
FOREIGN KEY (idFornecedor) REFERENCES Fornecedor(id)
);
select * from NotaEntrada;

-- Itens da Nota de Entrada
CREATE TABLE NotaEntradaItem (
idNotaEntrada INT NOT NULL,
idMaterial INT NOT NULL,
quantidade DOUBLE NOT NULL,
FOREIGN KEY (idNotaEntrada) REFERENCES NotaEntrada(id),
FOREIGN KEY (idMaterial) REFERENCES Material(id)
);
select * from NotaEntradaItem;