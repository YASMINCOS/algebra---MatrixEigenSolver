package org.example;

import java.util.Scanner;

public class MatrixEigenSolver {
    private static final int N = 4;
    private double[][] matrix;
    private double[][] eigenvectors;
    private double[] eigenvalues;

    public MatrixEigenSolver() {
        matrix = new double[N][N];
        eigenvectors = new double[N][N];
        eigenvalues = new double[N];
    }

    // Método para ler a matriz do usuário
    public void readMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite os elementos da matriz 4x4:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("Digite o elemento [%d][%d]: ", i+1, j+1);
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    // Método do Power Iteration para encontrar o maior autovalor e autovetor
    private void powerIteration(double[][] matrix, double[] vector, int maxIterations) {
        for (int iter = 0; iter < maxIterations; iter++) {
            // Multiplicação matriz-vetor
            double[] newVector = new double[N];
            for (int i = 0; i < N; i++) {
                newVector[i] = 0;
                for (int j = 0; j < N; j++) {
                    newVector[i] += matrix[i][j] * vector[j];
                }
            }

            // Normalização
            double norm = 0;
            for (int i = 0; i < N; i++) {
                norm += newVector[i] * newVector[i];
            }
            norm = Math.sqrt(norm);

            for (int i = 0; i < N; i++) {
                vector[i] = newVector[i] / norm;
            }
        }
    }

    private double[] matrixVectorMultiply(double[][] matrix, double[] vector) {
        double[] result = new double[N];
        for (int i = 0; i < N; i++) {
            result[i] = 0;
            for (int j = 0; j < N; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    // Método para calcular o quociente de Rayleigh
    private double rayleighQuotient(double[][] matrix, double[] vector) {
        double[] Av = matrixVectorMultiply(matrix, vector);
        double numerator = 0;
        double denominator = 0;
        for (int i = 0; i < N; i++) {
            numerator += vector[i] * Av[i];
            denominator += vector[i] * vector[i];
        }
        return numerator / denominator;
    }

    // Método para deflação da matriz
    private double[][] deflateMatrix(double[][] matrix, double[] eigenvector, double eigenvalue) {
        double[][] result = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = matrix[i][j] - eigenvalue * eigenvector[i] * eigenvector[j];
            }
        }
        return result;
    }

    // Método principal para encontrar autovalores e autovetores
    public void solve() {
        double[][] currentMatrix = matrix.clone();
        for (int k = 0; k < N; k++) {
            // Inicializar vetor aleatório
            double[] vector = new double[N];
            for (int i = 0; i < N; i++) {
                vector[i] = Math.random();
            }

            // Aplicar Power Iteration
            powerIteration(currentMatrix, vector, 100);

            // Calcular autovalor usando quociente de Rayleigh
            eigenvalues[k] = rayleighQuotient(currentMatrix, vector);

            // Armazenar autovetor
            for (int i = 0; i < N; i++) {
                eigenvectors[i][k] = vector[i];
            }

            // Deflação da matriz
            if (k < N-1) {
                currentMatrix = deflateMatrix(currentMatrix, vector, eigenvalues[k]);
            }
        }
    }

    // Método para verificar a diagonalização
    public void verifyDiagonalization() {
        System.out.println("\nVerificação da Diagonalização:");

        // Calcular A * v = λ * v para cada autovalor/autovetor
        for (int i = 0; i < N; i++) {
            double[] v = new double[N];
            for (int j = 0; j < N; j++) {
                v[j] = eigenvectors[j][i];
            }

            double[] Av = matrixVectorMultiply(matrix, v);
            double[] lambdaV = new double[N];
            for (int j = 0; j < N; j++) {
                lambdaV[j] = eigenvalues[i] * v[j];
            }

            System.out.printf("\nPara autovalor λ%d = %.4f:\n", i+1, eigenvalues[i]);
            System.out.println("A*v ≈ λ*v");
            for (int j = 0; j < N; j++) {
                System.out.printf("%.4f ≈ %.4f\n", Av[j], lambdaV[j]);
            }
        }
    }

    //Método para imprimir resultados
    public void printResults() {
        System.out.println("\nAutovalores encontrados:");
        for (int i = 0; i < N; i++) {
            System.out.printf("λ%d = %.4f\n", i+1, eigenvalues[i]);
        }

        System.out.println("\nAutovetores correspondentes:");
        for (int i = 0; i < N; i++) {
            System.out.printf("v%d = [", i+1);
            for (int j = 0; j < N; j++) {
                System.out.printf("%.4f", eigenvectors[j][i]);
                if (j < N-1) System.out.print(", ");
            }
            System.out.println("]");
        }

        verifyDiagonalization();
    }

    public static void main(String[] args) {
        MatrixEigenSolver solver = new MatrixEigenSolver();
        solver.readMatrix();
        solver.solve();
        solver.printResults();
    }
}
