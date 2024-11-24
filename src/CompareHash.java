package src;


public class CompareHash {
    private static final int TAMANHO_TABELA = 30;
    public static void main(String [] args) {
        //indexar arquivos
        HashTableService hashTableDivide = new HashTableService(TAMANHO_TABELA); 
        HashTableService hashTableDjb2 = new HashTableService(TAMANHO_TABELA); 
        ArchiveService [] conteudo = new ArchiveService[TAMANHO_TABELA];
        TrieService trie = new TrieService();
        FileContent file = new FileContent();
        long startDivisao, elapsedDivisao,startDjb2, elapsedDjb2, startTrie, elapsedTrie;
        double mTrie = 0.0;
        String[] words = {
            "adoption", "leadership", "transparency", "public", "expertise", "oversight", "accessibility", "resources",
            "adaptation", "monitoring", "performance", "analysis", "review", "guidelines", "outcomes", "systems",
            "security", "governance", "development", "quality", "advancements", "incentives", "design", "policies",
            "legislation", "functionality", "responsibility", "responsible", "stakeholders", "management", "employees",
            "insights", "tools", "practices", "barriers", "metrics", "of", "technology", "recommendations",
            "effectiveness", "capacity", "cities", "processes", "partnerships", "compliance", "automation", "methods",
            "dynamics", "maturity", "algorithms", "AI", "solutions", "government", "risks", "challenges", "frameworks",
            "accountability", "engagement", "integration", "collaboration", "workforce", "data", "procurement",
            "institutional", "standards", "evaluation", "vendors", "applications", "communication", "optimization",
            "implementation", "contracts", "palavraInexistente","palavraInexistente1","palavraInexistente2","palavraInexistente3","palavraInexistente4",
            "palavraInexistente5","palavraInexistente6","palavraInexistente7","palavraInexistente8","palavraInexistente9",
        };
        
        conteudo = file.getFiles("C:/Users/rcoenga/Documents/Projetos/Trabalho_de_Estrutura_de_dados/Artigos em txt");
        //inserção nas tabelas hash
        //Divisão
        startDivisao = System.currentTimeMillis();
        for(int i = 0; i< conteudo.length;i++)
        {
            hashTableDivide.inserirComHashDivisao(conteudo[i].getArchive(), TAMANHO_TABELA);
        }
        elapsedDivisao = System.currentTimeMillis();
        System.out.print("Tempo de inserção na hash por divisão:");
        System.out.printf("%d.4\n",elapsedDivisao - startDivisao);
        //DJB2
        startDjb2 = System.currentTimeMillis();
        for(int i = 0; i< conteudo.length;i++)
        {
            hashTableDjb2.inserirComHashDJB2(conteudo[i].getArchive());
        }
        elapsedDjb2 = System.currentTimeMillis();
        System.out.print("Tempo de inserção na hash por djb2:");
        System.out.printf("%d.4\n",elapsedDjb2 - startDjb2);
        for(int i = 0; i< conteudo.length;i++)
        {
            trie.insert(conteudo[i].getConteudo(),conteudo[i].getNome());
        }
        //tempo de busca
        for(int i=0;i<words.length; i++)
        {
            System.out.println(words[i]);
            startTrie = System.currentTimeMillis();
            trie.search(words[i]);
            elapsedTrie = System.currentTimeMillis();
            mTrie = mTrie + (elapsedTrie - startTrie);
        }
        System.out.print("Tempo medio de busca na trie:");
        System.out.printf("%f.4\n",(mTrie/100));
    }
}
