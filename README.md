# Projeto RaceTest

## Descrição do projeto
Este é um projeto desenvolvimendo na linguagem Java, tendo a sua camada de apresentação via WEB, utilizando Thymeleaf.</br>
A aplicação foi construída utilizando a IDE Eclipse e está utilizando o Spring-boot para subir um servidor Jetty.</br>
O projeto não utiliza nenhum banco de dados.

## Ambiente de desenvolvimento e execução
Para executar este projeto, será necessário ter um computador rodando Windows ou Linux, com JDK e Maven instalados.</br>
Configurar as variáveis de ambiente para o Java e Maven facilitará a tarefa de compilação.

## Baixando o projeto 
Após clonar o projeto, será necessário rodar o seguinte comando maven no console:
* mvn clean install
* mvn eclipse:eclipse
* mvn spring-boot:run</br>

O primeiro comando irá limpar a pasta do projeto e baixar as dependências para que ele possa ser compilado.</br>
O segundo comando irá preparar a estrutura de pastas para que ele possa ser aberto pelo Eclipse. Este passo pode ser ignorado se a IDE utilizada não foi o Eclipse</br>
O último comando irá terminar de criar o ambiente necessário para a aplicação e subirá o servidor Tomcat. Este passo é importante para preparar toda a estrutura de pastas dos recursos da aplicação.

## Utilizando a aplicação
Após o servidor da aplicação iniciar, será possível acessá-la pelo endereço: localhost:8080.</br>
Será exibida uma tela com um campo para a escolha do arquivo. Este arquivo deve ser um arquivo de texto com o canteúdo do log da corrida que está no enunciado da prova e precisa estar com encoding UTF-8. Existe um arquivo com o nome log.txt dentro da pasta \src\main\resources, ele foi utilizado para os testes da aplicação.</br>
Após clicar no botão Submit, o arquivo será avaliado e, se for válido, será exibida uma tela com os resultados da corrida.

## Nota importante
A aplicação foi desenvolvida e testada no ambiente Windows, mas as funcionalidades foram desenvolvidas de forma que o projeto possa ser executado em um ambiente Linux.
