# Revisão rápida da base de código e tarefas sugeridas

## 1) Tarefa para corrigir erro de digitação
**Problema encontrado:** O nome do método de repositório para busca por veículo começa com `findByapplicationVehicle...` ("application" com inicial minúscula), o que foge do padrão de nomenclatura usado no restante da base e reduz legibilidade.

**Tarefa sugerida:** Renomear `findByapplicationVehicleContainingIgnoreCaseOrderByNameAsc` para `findByApplicationVehicleContainingIgnoreCaseOrderByNameAsc` em todo o projeto (repositório, controller e testes), garantindo consistência de estilo.

## 2) Tarefa para corrigir bug
**Problema encontrado:** No teste `should_update_part_by_id`, após atualizar a entidade, asserções comparam o registro salvo com os valores **antigos** (`part3`) em vez dos valores atualizados (`updatedPart3`). Isso invalida o objetivo do teste e pode mascarar regressões.

**Tarefa sugerida:** Corrigir as asserções para validar os campos atualizados (nome, veículo e pesos) e manter apenas o `id` como inalterado.

## 3) Tarefa para ajustar comentário de código / discrepância de documentação
**Problema encontrado:** O nome do teste `should_delete_all_tutorials` menciona "tutorials", mas a aplicação e o domínio usam "parts/peças".

**Tarefa sugerida:** Renomear o método para algo alinhado ao domínio (ex.: `should_delete_all_parts`) e revisar nomes/comentários similares para manter a documentação interna coerente.

## 4) Tarefa para melhorar um teste
**Problema encontrado:** Não há cobertura explícita para o comportamento de `GET /parts` quando os dois filtros (`name` e `vehicle`) são enviados ao mesmo tempo; a implementação atual prioriza `vehicle` e ignora `name` nesse cenário.

**Tarefa sugerida:** Criar teste de controller para o caso com ambos os parâmetros e decidir/registar comportamento esperado (combinar filtros, priorizar um filtro ou rejeitar requisição inválida com 400).

## 5) Tarefas trazidas do README.md (seção ToDo)
- [ ] Criar uma segunda entidade **vehicle** no banco de dados e fazer o relacionamento entre elas. Ficaria a entidade **part** com um relacionamento de NxN com vehicle. Uma peça pode pertencer a vários veículos e um veículo pode conter várias peças.
- [ ] Criar endpoints no backend para manipular o relacionamento entre as entidades `vehicle` e `part`.
- [ ] Criar novas telas no frontend de manipulação e exibição das entidades `vehicle` e `part` e seus relacionamentos.
