package huffman;

import java.io.File;

public class HuffmanTest {

    private static void validateFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("Arquivo de entrada inválido ou inexistente: " + filePath);
        }
        System.out.println("Arquivo de entrada validado com sucesso: " + filePath);
    }

    private static long getFileSize(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            return file.length();
        } else {
            throw new RuntimeException("Não foi possível acessar o arquivo: " + filePath);
        }
    }

    private static void logCompressionStats(long originalSize, long compressedSize) {
        System.out.println("Tamanho do arquivo original: " + originalSize + " bytes");
        System.out.println("Tamanho do arquivo comprimido: " + compressedSize + " bytes");
        if (originalSize > 0) {
            double compressionRatio = ((double) compressedSize / originalSize) * 100;
            System.out.printf("Taxa de compressão: %.2f%%\n", compressionRatio);
        }
    }

    private static void logDecompressionStats(long originalSize, long decompressedSize) {
        System.out.println("Tamanho do arquivo descomprimido: " + decompressedSize + " bytes");
        if (originalSize == decompressedSize) {
            System.out.println("O tamanho do arquivo descomprimido é igual ao original.");
        } else {
            System.err.println("O tamanho do arquivo descomprimido difere do original!");
        }
    }

    private static boolean compareFiles(String originalFilePath, String decompressedFilePath) throws Exception {
        try (var original = new java.io.BufferedReader(new java.io.FileReader(originalFilePath));
             var decompressed = new java.io.BufferedReader(new java.io.FileReader(decompressedFilePath))) {

            String originalLine, decompressedLine = null;
            while ((originalLine = original.readLine()) != null && (decompressedLine = decompressed.readLine()) != null) {
                if (!originalLine.equals(decompressedLine)) {
                    return false;
                }
            }
            return originalLine == null && decompressedLine == null;
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "entrada.txt"; // Substitua pelo caminho do arquivo original
        String compressedFilePath = "saida.huff";
        String decompressedFilePath = "saida_decodificada.txt";

        try {
            validateFile(inputFilePath);

            System.out.println("Iniciando compressão...");
            long originalSize = getFileSize(inputFilePath);
            HuffmanBase.compressFile(inputFilePath, compressedFilePath);
            long compressedSize = getFileSize(compressedFilePath);
            logCompressionStats(originalSize, compressedSize);

            System.out.println("\nIniciando descompressão...");
            HuffmanBase.decompressFile(compressedFilePath, decompressedFilePath);
            long decompressedSize = getFileSize(decompressedFilePath);
            logDecompressionStats(originalSize, decompressedSize);

            System.out.println("\nVerificando integridade...");
            if (compareFiles(inputFilePath, decompressedFilePath)) {
                System.out.println("A descompressão preservou o conteúdo original. Tudo está funcionando corretamente!");
            } else {
                System.err.println("Erro: O conteúdo descomprimido difere do original!");
            }
        } catch (Exception e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
        }
    }
}
