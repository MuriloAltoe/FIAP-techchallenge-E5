# SUS Triage API

## Visão Geral

A **SUS Triage API** é uma aplicação backend desenvolvida em Java com Spring Boot cujo objetivo é organizar e priorizar atendimentos de triagem no contexto do Sistema Único de Saúde (SUS). O sistema registra triagens, classifica o risco dos pacientes, organiza uma fila priorizada e controla o fluxo de atendimento de forma consistente e auditável.

O foco da solução é reduzir desorganização, melhorar o tempo de resposta para casos críticos e fornecer uma base técnica sólida para evoluções futuras.

---

## Problema

Unidades de saúde frequentemente enfrentam dificuldades na organização das filas de atendimento, o que pode resultar em atrasos para pacientes em situação de maior risco. A ausência de um controle claro de prioridade e de estado do atendimento impacta diretamente a qualidade do serviço prestado.

---

## Objetivo

Fornecer uma API REST capaz de:

* Registrar triagens de pacientes
* Classificar automaticamente o risco clínico
* Organizar uma fila priorizada de atendimento
* Controlar o ciclo de vida da triagem (espera, atendimento e finalização)

---

## Arquitetura da Solução

O projeto segue uma arquitetura em camadas:

* **Controller**: exposição dos endpoints REST
* **Service**: regras de negócio e fluxo do domínio
* **Repository**: persistência de dados (JPA)
* **Domain/Entity**: entidades do negócio
* **Strategy**: classificação de risco desacoplada

### Padrões aplicados

* Strategy Pattern para classificação de risco
* Separação clara de responsabilidades
* Máquina de estados para controle da triagem
* Exceções de domínio (`BusinessException`)

---

## Fluxo de Triagem

A triagem segue um fluxo de estados bem definido:

```
WAITING → IN_PROGRESS → FINISHED
```

* **WAITING**: aguardando atendimento
* **IN_PROGRESS**: atendimento em andamento
* **FINISHED**: atendimento finalizado

Somente triagens em estado `WAITING` participam da fila priorizada.

---

## Endpoints da API

### Criar triagem

```
POST /triages
```

Registra uma nova triagem e classifica o risco do paciente.

---

### Obter fila priorizada

```
GET /triages/queue
```

Retorna a fila de triagens ordenada por:

1. Nível de risco
2. Tempo de espera

---

### Iniciar atendimento

```
POST /triages/{id}/start
```

Move a triagem de `WAITING` para `IN_PROGRESS`.

---

### Finalizar atendimento

```
POST /triages/{id}/finish
```

Move a triagem de `IN_PROGRESS` para `FINISHED`.

---

## Testes

O projeto possui testes unitários focados nas regras de negócio, utilizando:

* JUnit 5
* Mockito

Os testes validam:

* Criação de triagem
* Ordenação da fila priorizada
* Transições corretas de estado
* Tratamento de exceções de negócio

---

## Tecnologias Utilizadas

* Java 17+
* PostgreSQL
* Maven
* Docker

---

## Execução do Projeto

### Pré-requisitos

* Java 17+
* Maven
* Banco de dados PostgreSQL
* Docker

### Executar localmente

```bash
docker-compose up --build
```

---

## Evoluções Futuras

Este projeto foi desenvolvido com foco no escopo proposto, porém possui arquitetura preparada para evoluções, tais como:

* Timeout automático de espera por nível de risco
* Dashboard com métricas de atendimento
* Autenticação e controle de acesso por perfil
* Integração com prontuário eletrônico
* Auditoria completa de atendimentos

---

## Considerações Finais

A SUS Triage API atende integralmente ao escopo proposto, entregando uma solução organizada, extensível e tecnicamente consistente para a gestão de triagens em unidades de saúde. O projeto foi desenvolvido priorizando clareza de regras de negócio, testabilidade e facilidade de manutenção.
