package src;

import hashing.HashTable;

public class HashTableService {
    private HashTable<Integer, String> hashTable;

    public HashTableService(int tamanhoTabela) {
        this.hashTable = new HashTable<>(tamanhoTabela);
    }

    public HashTable<Integer, String> getHashTable() {
        return hashTable;
    }

    public boolean inserirComHashDivisao(String conteudo, int M) {
        int chave = hashDivisao(conteudo, M);
        return hashTable.put(chave, conteudo);
    }

    public boolean inserirComHashDJB2(String conteudo) {
        int chave = hashDJB2(conteudo);
        return hashTable.put(chave, conteudo);
    }


    public int hashDivisao(String texto, int M) {
        int soma = 0;
        for (char c : texto.toCharArray()) {
            soma += (int) c;
        }
        return soma % M;
    }

    public int hashDJB2(String texto) {
        long hash = 5381;
        for (char c : texto.toCharArray()) {
            hash = ((hash << 5) + hash) + c; // hash * 33 + c
        }
        return (int) (hash % Integer.MAX_VALUE);
    }
}
