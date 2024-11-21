package huffman;

public class HuffmanTrieNode implements Comparable<HuffmanTrieNode>{
	char character; // Caractere armazenado no nó
	int frequency; // Frequência do caractere
	HuffmanTrieNode left, right; // Filhos à esquerda e à direita
	
	// Construtor para o nó intermediário
	public HuffmanTrieNode(int frequency, HuffmanTrieNode left, HuffmanTrieNode right) { 
		this.character = '\0'; // Não tem caractere
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
	
	// Construtor para o nó folha
	public HuffmanTrieNode(char character, int frequency) { 
		this.character = character; 
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}

	@Override
	public int compareTo(HuffmanTrieNode otherNode) {
		return this.frequency - otherNode.frequency;
	}
}