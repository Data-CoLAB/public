# README

Olá! Este desafio foi feito usando [Quarkus](https://quarkus.io/).
Para o executar basta correr instalar o [Maven](https://maven.apache.org/install.html) e correr usando o seguinte comando:

    mvn clean compile quarkus:dev

No entanto o presente desafio pode ser de forma simples adaptado para qualquer outra framework devido à sua pequena codebase.

## Contexto
Este projeto tem como objectivo processar ficheiros existentes na folder `src/main/resources/files` e imprimir o seu conteúdo para log (simulando uma eventual camada de base de dados). Além disto todos os caracteres do conteúdo de cada ficheiro processado vão sendo somados a um counter que tem depois um serviço exposto para ser consultado. É ainda possível consultar a lista de projetos já processados.
O projeto encontra-se dividido em 3 camadas:

 - Rest.java: Serviços HTTP expostos
 - Workflow: Camada de negócio que lê os ficheiros indicados e simula o seu processamento
 - Dao: Camada que guarda o processamento (simula camada de dados apenas escrevendo para log).

Este projecto expõe 3 serviços REST:

 - POST: /text-parser/api/process - Recebe um array de string com nomes de ficheiros para serem processados
 - GET: /text-parser/api/done - Devolve a lista de nomes de ficheiros já processados
 - GET: /text-parser/api/totalcharacters - Devolve o número de caracteres total já lidos

## Objetivo do exercicio

Tendo em conta a explicação acima e sendo a única regra, além de que o projeto cumpra os requisitos acima descritos, o facto de o mesmo ficheiro não poder ser processado 2 vezes:

 - Analisar o código do projeto em questão tendo em conta os objetivos e finalidade proposta
 - Identificar melhorias em termos de codificação, estrutura e arquitetura
 - Identificar eventuais bugs

O resultado desta análise deverá ser feito num formato de tópicos sendo cada 1 categorizado segundo a seguinte criticidade: Critica, Média, Baixa.
Juntamente com cada identificação deve constar um resumo (uma frase chega) com a descrição do problema e se possível uma sugestão de melhoria/correção (não é preciso implementar).
