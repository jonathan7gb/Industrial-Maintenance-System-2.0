# Sistema de Manutenção Industrial (Desafio) — README

Resumo
-----
Aplicação em Java (programação estruturada + OO) para gerenciar almoxarifado industrial. Permite cadastrar fornecedores e materiais, registrar notas de entrada (compras), criar e gerenciar requisições de materiais, e controlar estoque. Usa JDBC para persistência em banco MySQL e aplica padrão Model + DAO.

Funcionalidades principais
--------------------------
- Cadastrar Fornecedor (nome, CNPJ — único)
- Cadastrar Material (nome, unidade, estoque inicial)
- Registrar Nota de Entrada (vinculada a fornecedor; atualiza estoque)
- Criar Requisição de Material (setor, itens, quantidades) — status: PENDENTE / ATENDIDA / CANCELADA
- Atender Requisição (verifica estoque, atualiza estoque e status)
- Menu de console simples para navegar pelas operações

Regras de negócio (resumidas)
-----------------------------
- CNPJ e nome do fornecedor obrigatórios; CNPJ único.
- Nome do material obrigatório; nome único; estoque >= 0.
- Nota de entrada aumenta o estoque dos materiais informados.
- Requisição criada com status PENDENTE; só requisições PENDENTE podem ser atendidas.
- Ao atender, verifica-se se o estoque é suficiente; se sim, subtrai e marca ATENDIDA; caso contrário, operação recusada.
- Itens já associados a uma nota ou a uma requisição não devem ser exibidos novamente ao montar a mesma nota/requisição.

Banco de dados (exemplo de criação)
-----------------------------------
Abaixo está a sugestão das tabelas (MySQL):

```sql
-- Fornecedores
CREATE TABLE Fornecedor (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  cnpj VARCHAR(14) UNIQUE NOT NULL
);

-- Materiais
CREATE TABLE Material (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  unidade VARCHAR(20) NOT NULL,
  estoque DOUBLE NOT NULL
);

-- Requisições de Material
CREATE TABLE Requisicao (
  id INT PRIMARY KEY AUTO_INCREMENT,
  setor VARCHAR(50) NOT NULL,
  dataSolicitacao DATE NOT NULL,
  status VARCHAR(20) NOT NULL -- PENDENTE / ATENDIDA / CANCELADA
);

-- Itens da Requisição
CREATE TABLE RequisicaoItem (
  idRequisicao INT NOT NULL,
  idMaterial INT NOT NULL,
  quantidade DOUBLE NOT NULL,
  PRIMARY KEY (idRequisicao, idMaterial),
  FOREIGN KEY (idRequisicao) REFERENCES Requisicao(id),
  FOREIGN KEY (idMaterial) REFERENCES Material(id)
);

-- Notas de Entrada
CREATE TABLE NotaEntrada (
  id INT PRIMARY KEY AUTO_INCREMENT,
  idFornecedor INT NOT NULL,
  dataEntrada DATE NOT NULL,
  FOREIGN KEY (idFornecedor) REFERENCES Fornecedor(id)
);

-- Itens da Nota de Entrada
CREATE TABLE NotaEntradaItem (
  idNotaEntrada INT NOT NULL,
  idMaterial INT NOT NULL,
  quantidade DOUBLE NOT NULL,
  PRIMARY KEY (idNotaEntrada, idMaterial),
  FOREIGN KEY (idNotaEntrada) REFERENCES NotaEntrada(id),
  FOREIGN KEY (idMaterial) REFERENCES Material(id)
);
```

Dependências (Maven)
--------------------
Adicionar conector MySQL:

```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <version>8.0.33</version>
</dependency>
```

Fluxo de menu (console) — exemplo de opções
------------------------------------------
1) Cadastrar Fornecedor
2) Cadastrar Material
3) Registrar Nota de Entrada
4) Criar Requisição de Material
5) Atender Requisição
0) Sair

Notas de implementação
----------------------
- Use PreparedStatement para evitar SQL injection e facilitar tratamento de parâmetros.
- Nas transações que envolvem múltiplas operações (ex.: criar nota + inserir itens + atualizar estoque), use controle de transação (setAutoCommit(false)/commit/rollback).
- Valide entradas do usuário (strings obrigatórias, números positivos).
- Trate exceções SQL e apresente mensagens amigáveis no console.
- Reutilize métodos DAO para buscar listas (por ex.: listar fornecedores, materiais, requisições PENDENTES).
- Ao listar materiais para associar a uma nota/requisição, filtre os que já foram adicionados àquela nota/requisição.

Testes manuais sugeridos
------------------------
1. Inserir fornecedor com CNPJ duplicado → deve falhar.
2. Inserir material com estoque negativo → deve impedir.
3. Registrar nota de entrada com vários itens → estoque aumenta corretamente.
4. Criar requisição com quantidade maior que o estoque → deve impedir.
5. Atender requisição com estoque suficiente → estoque reduz; status ATENDIDA.
6. Tentar atender requisição com estoque insuficiente → operação negada; status inalterado.
