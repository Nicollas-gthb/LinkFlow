# ADR-001: Estrutura de pacotes por camada técnica

**Data:** 2026-07-03  
**Status:** Aceito

## Contexto

O backend precisa de uma organização de código clara que separe responsabilidades e facilite a manutenção.

## Decisão

Organizar os pacotes por camada técnica: `domain`, `repository`, `service`, `controller`, `dto`, `config`, `exception`, `jobs`, `messaging`.

## Motivo

- Reflete diretamente a arquitetura em camadas do Spring Boot
- Facilita localizar onde cada tipo de classe deve viver
- É o padrão amplamente adotado no mercado para projetos Spring de médio porte

## Consequências

- Cada camada tem responsabilidade única e bem definida
- Controllers não contêm lógica de negócio
- Entidades do banco nunca são expostas diretamente na API