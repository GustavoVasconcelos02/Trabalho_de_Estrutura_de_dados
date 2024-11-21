package src;

import java.util.LinkedList;

import archive.Archive;
import hashing.HashTable;

public class HashTableService {
    private HashTable<Integer, Archive> hashTable;

    public HashTableService(int tamanhoTabela) {
        this.hashTable = new HashTable<>(tamanhoTabela);
    }

    public HashTable<Integer, Archive> getHashTable() {
        return hashTable;
    }

    public boolean inserirComHashDivisao(Archive archive, int M) {
        int chave = hashDivisao(archive.getNome(), M);
        return hashTable.put(chave, archive);
    }

    public boolean inserirComHashDJB2(Archive archive) {
        int chave = hashDJB2(archive.getNome());
        return hashTable.put(chave, archive);
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
