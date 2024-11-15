
# MatrixEigenSolver

Este projeto contém uma implementação em Java de um solver para autovalores e autovetores de uma matriz (4x4) inserida pelo usuário. Utilizando o método de Power Iteration e o quociente de Rayleigh, o programa calcula os autovalores e autovetores da matriz e exibe os resultados, permitindo a verificação da diagonalização da matriz.

## Funcionalidades

- **Leitura da Matriz:** Permite que o usuário insira os elementos de uma matriz 4x4 via console.
- **Cálculo de Autovalores e Autovetores:** Utiliza o método de Power Iteration para aproximar o maior autovalor e o autovetor correspondente.
- **Deflação de Matriz:** Reduz a matriz original iterativamente para encontrar os autovalores e autovetores restantes.
- **Verificação da Diagonalização:** 
- **Exibição dos Resultados:** Exibe os autovalores e os autovetores encontrados, além dos resultados da verificação.

## Estrutura do Código

- **Classe `MatrixEigenSolver`:** Implementa os métodos para leitura, cálculo de autovalores e autovetores, e verificação da diagonalização.
  - `readMatrix()`: Lê a matriz 4x4 do usuário.
  - `powerIteration()`: Realiza a iteração de potência para calcular o autovalor e o autovetor dominante.
  - `rayleighQuotient()`: Calcula o autovalor usando o quociente de Rayleigh.
  - `deflateMatrix()`: Aplica a deflação para encontrar autovalores subsequentes.
  - `verifyDiagonalization()`: Verifica a diagonalização.
  - `printResults()`: Imprime os autovalores e autovetores, e verifica a diagonalização.

## Exemplo de Uso

1. Execute a aplicação.
2. Insira os valores para a matriz 4x4 quando solicitado.
3. O programa exibirá os autovalores e autovetores encontrados, além da verificação da diagonalização.

## Executando o Código

Para compilar e executar o programa, siga estes passos:

1. Compile o programa:
   ```bash
   javac -d . MatrixEigenSolver.java
   ```

2. Execute o programa:
   ```bash
   java org.example.MatrixEigenSolver
   ```

## Exemplo de Saída

Após inserir a matriz, o programa exibirá:

- Os autovalores encontrados.
- Os autovetores correspondentes a cada autovalor.
- A verificação de diagonalizaçao.

## Requisitos

- Java 17 ou superior.

## Notas

Este solver é uma implementação simples e educativa para matrizes 4x4 e tem limitações quanto à precisão para matrizes de maior dimensão ou com valores complexos. 

