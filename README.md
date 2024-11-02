# Projeto de Indexação e Busca de Textos em Java

## Descrição Geral

Este projeto consiste na construção de um sistema em Java para indexação e busca de palavras em documentos de forma eficiente. O sistema abrange todo o processo, desde a inserção de documentos até a busca de palavras específicas. O corpus utilizado consiste em 30 artigos científicos do arXiv, convertidos para texto plano (.txt) e processados pelo programa. O objetivo é proporcionar uma maneira rápida e eficaz de realizar consultas em grandes volumes de texto.

### Estruturas de Dados Utilizadas
Para tornar o sistema eficiente e escalável, foram implementadas as seguintes estruturas de dados:

**Tabela Hash:** Utilizada para armazenar os textos compactados e associá-los a uma chave gerada por funções de hash. Foram implementadas duas funções de hash (por Divisão e DJB2) para fins de comparação de desempenho.

**Trie (Árvore Digital):** Responsável por indexar as palavras, facilitando buscas rápidas e eficientes no corpus de documentos.
Compressão de Textos
Os textos são compactados utilizando o Algoritmo de Huffman, que reduz o espaço de armazenamento, mantendo a integridade e acessibilidade dos dados.

### Funcionalidades Principais
 - **Inserção de Documentos:** O usuário pode inserir um conjunto de documentos para serem indexados.

 - **Escolha da Função de Hash:** O sistema permite ao usuário escolher entre as funções de hash implementadas para associar documentos.

 - **Busca de Palavras:** O usuário pode buscar palavras específicas, com o sistema retornando os documentos onde as palavras foram encontradas.

#### Medição de Desempenho
Foram realizadas medições de desempenho para avaliar o tempo de indexação, tempo de busca, e consumo de memória das estruturas de dados. Esses testes foram realizados com expressões de busca previamente definidas e documentadas, garantindo resultados precisos e consistentes.

#### Conversão de PDFs
Os documentos no formato PDF foram convertidos para texto plano utilizando a biblioteca PyMuPDF. Essa conversão permite uma melhor manipulação do conteúdo para indexação e busca.
