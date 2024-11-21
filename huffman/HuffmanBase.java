package huffman;

import java.io.*;
import java.util.*;

public class HuffmanBase {

    public static class Node {
        public int frequency;
        public char character;
        public Node left;
        public Node right;

        public Node(int frequency, char character) {
            this.frequency = frequency;
            this.character = character;
            this.left = null;
            this.right = null;
        }

        public Node(int frequency, Node left, Node right) {
            this.frequency = frequency;
            this.character = '\0';
            this.left = left;
            this.right = right;
        }
    }

    public static class FrequencyComparator implements Comparator<Node> {
        @Override
        public int compare(Node x, Node y) {
            return x.frequency - y.frequency;
        }
    }

    public static void generateCodes(Node root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
        }
        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    public static Node buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new FrequencyComparator());

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            Node node = new Node(entry.getValue(), entry.getKey());
            queue.add(node);
        }

        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();

            Node merged = new Node(left.frequency + right.frequency, left, right);
            queue.add(merged);
        }

        return queue.poll();
    }

    public static void compressFile(String inputFilePath, String outputFilePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : content.toString().toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        Node root = buildHuffmanTree(frequencyMap);

        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        StringBuilder encodedContent = new StringBuilder();
        for (char c : content.toString().toCharArray()) {
            encodedContent.append(huffmanCodes.get(c));
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFilePath))) {
            oos.writeObject(huffmanCodes);
            oos.writeObject(encodedContent.toString());
        }

        System.out.println("Arquivo comprimido com sucesso! Saída: " + outputFilePath);
    }

    @SuppressWarnings("unchecked")
    public static void decompressFile(String inputFilePath, String outputFilePath) throws IOException, ClassNotFoundException {
        Map<Character, String> huffmanCodes;
        String encodedContent;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFilePath))) {
            Object huffmanCodesObject = ois.readObject();
            if (huffmanCodesObject instanceof Map<?, ?>) {
                huffmanCodes = (Map<Character, String>) huffmanCodesObject;
            } else {
                throw new ClassCastException("Erro: Objeto deserializado não é do tipo Map<Character, String>");
            }

            encodedContent = (String) ois.readObject();
        }

        Map<String, Character> reverseCodes = new HashMap<>();
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            reverseCodes.put(entry.getValue(), entry.getKey());
        }

        StringBuilder decodedContent = new StringBuilder();
        String temp = "";
        for (char bit : encodedContent.toCharArray()) {
            temp += bit;
            if (reverseCodes.containsKey(temp)) {
                decodedContent.append(reverseCodes.get(temp));
                temp = "";
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            bw.write(decodedContent.toString());
        }

        System.out.println("Arquivo descomprimido com sucesso! Saída: " + outputFilePath);
    }
}
