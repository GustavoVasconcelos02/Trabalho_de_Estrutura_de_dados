package huffman;

public class HuffmanTrieTest {
	public static void main (String [] a) {
		String input = "Olá, senhor Huffman! Obrigado por existir.";
		HuffmanTrie huffman = new HuffmanTrie(input);
		String compressed = huffman.compress("lado"),
			   decompressed = huffman.decompress(compressed);
		System.out.println("Mensagem comprimida: " + compressed);
		System.out.println("Mensagem descomprimida: " + decompressed);
		System.out.println(huffman.getHuffmanTable());
	}
}