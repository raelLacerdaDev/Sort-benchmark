import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

// aviso este codigo é um conversão do main.kt feito pelo gemini se possivel avaliar o meu código. desde já agradeço

public class Main {

    public static void main(String[] args) {
        // O caminho para o seu arquivo JSON. Atualize se for necessário.
        String filePath = "/Users/israellacerdagomessantos/IdeaProjects/SortBenchmark/src/main/kotlin/input.json";

        try {
            // Ler o conteúdo do arquivo JSON para uma string
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Usar Gson para desserializar a string JSON em um array de Strings
            Gson gson = new Gson();
            String[] arr = gson.fromJson(jsonContent, String[].class);

            // Criar cópias do array para que cada algoritmo ordene a lista original
            String[] arr2 = Arrays.copyOf(arr, arr.length);
            String[] arr3 = Arrays.copyOf(arr, arr.length);

            // --- Insertion Sort ---
            long beforeInsertion = System.currentTimeMillis();
            insertionSort(arr);
            long afterInsertion = System.currentTimeMillis();
            System.out.println("Insertion Sort: " + (afterInsertion - beforeInsertion) + " ms");

            // --- Bubble Sort ---
            long beforeBubble = System.currentTimeMillis();
            bubbleSort(arr2);
            long afterBubble = System.currentTimeMillis();
            System.out.println("Bubble Sort: " + (afterBubble - beforeBubble) + " ms");

            // --- Quick Sort ---
            long beforeQuick = System.currentTimeMillis();
            quickSort(arr3);
            long afterQuick = System.currentTimeMillis();
            System.out.println("Quick Sort: " + (afterQuick - beforeQuick) + " ms");

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void bubbleSort(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                // Em Java, usamos o método compareTo para comparar strings
                if (array[j].compareTo(array[j + 1]) > 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            // Se nenhum elemento foi trocado no loop interno, o array já está ordenado
            if (!swapped) {
                break;
            }
        }
    }

    public static void insertionSort(String[] array) {
        for (int k = 1; k < array.length; k++) {
            for (int j = k; j > 0; j--) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    String temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // Método público para iniciar o Quick Sort
    public static void quickSort(String[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    // Método auxiliar recursivo privado
    private static void quickSortRecursive(String[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSortRecursive(arr, begin, partitionIndex - 1);
            quickSortRecursive(arr, partitionIndex + 1, end);
        }
    }

    // Método para encontrar a partição
    private static int partition(String[] arr, int begin, int end) {
        String pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                String swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        String swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }
}
