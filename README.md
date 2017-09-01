# ContaCorrente

Este é um modelo que implementa os conceitos de persistência, mapeamento e consulta que vimos em sala de aula.

O projeto implementa o seguinte modelo:

![modeloreferenciahibernate](https://user-images.githubusercontent.com/20231710/29973794-20cb08f0-8f07-11e7-91d7-0f990a9d14fe.png)

## Mapeamentos
`@OneToOne` - Mapeamento um para um. Utilizado entre as classes `Cliente` e `ContaCorrente`

`@OneToMany` - Mapeamento um para muitos. Utilizado da classe `ContaCorrente` para a classe `Movimentacao`
Esse mapeamento tem duas propriedades adicionais na anotação: `mappedBy` indicando qual o campo na classe do lado n que fará o link (ou será a FK na tabela). E `cascade = CascadeType.ALL`, indicando que as alterações em `ContaCorrente`devem ser refletidas na movimentação. Observe que não estamos salvando os movimentos na classe de controle. Quando fazemos `sessio.save(contaCorrente)` todas as suas movimentações são automaticamente salvas. Docs do hibernate [aqui](http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#pc-cascade).

## Herança

A classe pai é anotada com `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`.

As estratégias de persistência de herança podem ser (doc do Hibernate [aqui](http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#entity-inheritance) ):
- `InheritanceType.SINGLE_TABLE`- Todas as classes são gravadas em uma única tabela. Na tabela é gerado um campo `DTYPE` que irá diferenciar o tipo de dado. Foi a estratégia que usei nesse modelo pois as classes `Credito` e `Debito` não tem dados.
Vantagens da estratégia: menor complicação no modelo de dados.
Desvantagens da estratégia: se as classes filhas tiverem muitos campos específicos, teremos muitas colunas vazias na tabela.
- `InheritanceType.TABLE_PER_CLASS`- cada classe tem uma tabela com todos os dados e ID próprio.
Vantagem: tabelas focadas no tipo de dado.
Desvantagem: leituras polimórficas serão feitas com UNION das tabelas.
- `InheritanceType.JOINED` - Cada classe tem sua tabela, no entando, a classe genérica tem uma tabela com os dados genéricos, e as classes específicas tem tabelas com os dados específicos. Vantagem: é o modelo mais versátil. Desvantagem: todos os queries precisam ter JOIN das tabelas.

