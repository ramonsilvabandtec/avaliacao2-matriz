package com.mycompany.entregavel;

import java.util.Scanner;

public class Matriz {

    static String[] clans; //nomes dos clans
    static int[][] estatistica; //vitorias derrotas e empates
    static double[] medias; // vetor da média de cada coluna
    static double[] razao; // vetor da razão entre derrotas e vitórias de cada linha/clã
    static int nroClans = 0;
    static int tamanho = -1;

    public Matriz(int sz) {
        clans = new String[sz];
        estatistica = new int[sz][3];
        medias = new double[3];
        razao = new double[sz];
        tamanho = sz;
    }

    public static boolean isValido(int i) {
        if (i > 0 && i <= 1024) {
            return true;
        } else {
            return false;
        }
    }

    public static double[] medias(int[][] matriz) {
        double[] medias = new double[3];
        int vitorias = 0;
        int derrotas = 0;
        int empates = 0;

        for (int cc = 0; cc < nroClans; cc++) {
            vitorias += matriz[cc][0];
            derrotas += matriz[cc][1];
            empates += matriz[cc][2];

        }
        medias[0] = vitorias / nroClans;
        medias[1] = derrotas / nroClans;
        medias[2] = empates / nroClans;
        return medias;
    }

    public static double[] razao(int[][] matriz) {
        double[] resultado = new double[tamanho];

        for (int cc = 0; cc < nroClans; cc++) {
            resultado[cc] = matriz[cc][0] / (double) matriz[cc][1];

        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        Scanner lerS = new Scanner(System.in);
        boolean fim = false;
        int opcao;
        while (!isValido(tamanho)) {
            try {
                System.out.println("\nDigite o máximo de registros : (entre 0 e 1024)");
                tamanho = ler.nextInt();
                if (!isValido(tamanho)) {
                    throw new Exception("Tamanho fora do padrão exigido");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }// enquanto tamanho estiver fora do range permitido, é jogada exception até tamanho ser válido

        Matriz DB = new Matriz(tamanho);
        while (!fim) {
            System.out.println("\nEscolha uma das opções:");
            System.out.println("1- Adicionar um clã");
            System.out.println("2- Finalizar");
            opcao = ler.nextInt();
            switch (opcao) {

                case 1: {
                    if (nroClans == tamanho) {
                        System.out.println("Limite de registros atingido, finalize a operação.");
                        break;
                    } else {
                        System.out.println("Digite o nome do clã : ");
                        DB.clans[nroClans] = lerS.nextLine();
                        System.out.println("Digite a quantidade de vitórias do " + DB.clans[nroClans] + " : ");
                        DB.estatistica[nroClans][0] = ler.nextInt();
                        System.out.println("Digite a quantidade de derrotas do " + DB.clans[nroClans] + " : ");
                        DB.estatistica[nroClans][1] = ler.nextInt();
                        System.out.println("Digite a quantidade de empates do " + DB.clans[nroClans] + " : ");
                        DB.estatistica[nroClans++][2] = ler.nextInt();
                        break;
                    }
                }
                case 2: {
                    razao = razao(estatistica);
                    medias = medias(estatistica);
                    fim = true;
                    break;
                }
                default: {
                    System.out.println("Opção \"" + opcao + "\" inválida.");
                    break;
                }

            }

        }
        System.out.printf("%-32s%10s%10s%10s%15s%n", "Nome do clã", "Vitórias", "Derrotas", "Empates", "Taxa Vit/Derr");
        for (int cc = 0; cc < nroClans; cc++) {
            System.out.printf("%-32s%10s%10s%10s           %.2f%n", clans[cc], estatistica[cc][0], estatistica[cc][1], estatistica[cc][2], razao[cc]);
            //não da pra transformar em float de 2 casas decimais, e ao mesmo tempo alinhar à direita.
            //então alinhei o %.2f manualmente à direita
        }

        System.out.printf("%-32s%10s%10s%10s%n", "Médias ", medias[0], medias[1], medias[2]);
    }

}
