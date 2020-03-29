import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.min;

public class Main {
    static File out_file;
    static PrintWriter pw;

    public static void main(String[] args) throws FileNotFoundException {
        out_file = new File("src/OUT_FILE_TASK");
        pw = new PrintWriter(out_file);
        Scanner input = new Scanner(System.in);
        System.out.print("Введите какую часть задачи вы хотите выполнить(1-Алгоритм Дейкстри;2-Алгоритм Флойда-Уоршола): ");
        String variant = input.nextLine();
        int[][] matrix_information = create_matrix_information("src/FILE_TASK1");
        int[][] matrixSumiz = create_matrix_sumiz(matrix_information);
        if (cheakMinus(matrix_information)) {
            System.out.println("В матрице есть отрицательные ребра!");
            return;
        }
        int[][] matrix_information_2 = create_matrix_information("src/FILE_TASK2");
        int[][] matrixSumiz2 = create_matrix_sumiz(matrix_information_2);

        if ("1".equals(variant)) {
            pw.println("Вы выбрали 1 вариант!");
            pw.println("========================");
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите стартовую вершину: ");
            int start = scan.nextInt();
            dikstrii2(start - 1, matrixSumiz);


        } else if ("2".equals(variant)) {
            pw.println("Вы выбрали 2 вариант!");
            pw.println("========================");


        } else {
            pw.println("Данного варианта не существует!");
            pw.close();
            return;

        }
        pw.close();
    }

    private static int[][] create_matrix_sumiz(int[][] matrixInfor) {
        int[][] matrix_sumiz = new int[matrixInfor.length][matrixInfor.length];
        for (int[] ints : matrix_sumiz) {
            Arrays.fill(ints, Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < matrix_sumiz.length; i++) {
            matrix_sumiz[matrixInfor[i][0] - 1][matrixInfor[i][1] - 1] = matrixInfor[i][2];
        }
        return matrix_sumiz;
    }


    private static int[][] create_matrix_information(String fileName) throws FileNotFoundException {
        File information = new File(fileName);
        Scanner scanner = new Scanner(information);
        String size_info = scanner.nextLine();
        String[] arr_info = size_info.split(" ");
        int[][] matrix_info = new int[Integer.parseInt(arr_info[0])][3];
        for (int i = 0; i < matrix_info.length; i++) {
            String info_for_matrix_info = scanner.nextLine();
            String[] info_for_matrix_info_arr = info_for_matrix_info.split(" ");
            matrix_info[i][0] = Integer.parseInt(info_for_matrix_info_arr[0]);
            matrix_info[i][1] = Integer.parseInt(info_for_matrix_info_arr[1]);
            matrix_info[i][2] = Integer.parseInt(info_for_matrix_info_arr[2]);
        }
        scanner.close();
        return matrix_info;
    }

    private static boolean cheakMinus(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][2] < 0) {
                return true;
            }
        }
        return false;
    }

    private static void deikstri(int start, int[][] matrixSmez) {
        System.out.print("  ");
        for (int i = 1; i <= matrixSmez.length; i++) {
            System.out.print(i + " ");
        }
        int count = 1;
        System.out.println();
        int INF = Integer.MAX_VALUE / 2;
        boolean[] used = new boolean[matrixSmez.length];
        int[] dist = new int[matrixSmez.length];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        while (true) {
            int v = -1;
            for (int i = 0; i < matrixSmez.length; i++)
                if (!used[i] && dist[i] < INF && (v == -1 || dist[v] > dist[i]))
                    v = i;
            if (v == -1) break;
            used[v] = true;
            for (int i = 0; i < matrixSmez.length; i++)
                if (!used[i] && matrixSmez[v][i] < INF)
                    dist[i] = min(dist[i], dist[v] + matrixSmez[v][i]);
            System.out.print(count + " ");
            outDistance(dist);
            System.out.println();
            count++;
        }

    }

    private static void dikstrii2(int start, int[][] matrixSmez) {
        int MyInf = Integer.MAX_VALUE / 2;
        boolean[] M = new boolean[matrixSmez.length];
        int[] l = new int[matrixSmez.length];
        Arrays.fill(l, MyInf);
        l[start] = 0;
        M[start] = true;
        int x = start;
        while (true) {
            for (int v = 0; v < matrixSmez.length; v++) {
                if (M[v] != true) {
                    l[v] = min(l[v], l[x] + matrixSmez[x][v]);
                }
            }
            int minIndex = 0;
            for (int i = 0; i < l.length; i++) {
                if (l[minIndex] > l[i]) {
                    minIndex = i;
                }
            }
            x = minIndex;
            outDistance(l);
            for (int i = 0; i < M.length; i++) {
                int count = 0;
                if(M[i]){
                    count++;
                }
                else{
                    break;
                }
                if(count==M.length){
                    return;
                }
            }
        }
    }

    private static void outDistance(int[] dist) {
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE / 2)
                System.out.print("∞ ");
            else
                System.out.print(dist[i] + " ");
        }
    }


}
