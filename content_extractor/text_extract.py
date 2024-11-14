import fitz  # PyMuPDF
import re
import os

def extract_text_from_pdf(pdf_path):
    """Extrai texto de um PDF usando PyMuPDF (fitz) e aplica pós-processamento para melhorar a formatação."""
    try:
        doc = fitz.open(pdf_path)
        texto_completo = []

        for page_num in range(len(doc)):
            page = doc.load_page(page_num)
            texto = page.get_text("text")
            texto = clean_text(texto)  # Limpeza de texto para melhorar a formatação
            texto_completo.append(texto)

        doc.close()
        return "\n\n".join(texto_completo)  # Retorna todas as páginas concatenadas
    except Exception as e:
        print(f"Erro ao extrair texto de {pdf_path}: {e}")
        return None

def clean_text(text):
    """Aplica Regex para formatar os textos extraidos."""
    # Remover quebras de linha isoladas (entre palavras)
    text = re.sub(r'(?<!\n)\n(?!\n)', ' ', text)

    # Substituir múltiplas novas linhas por uma única nova linha
    text = re.sub(r'\n\s*\n', '\n\n', text)

    # Remover espaços extras
    text = re.sub(r'[ ]+', ' ', text)

    return text

def process_pdfs_in_folder(folder_path, output_folder):
    """Processa todos os PDFs na pasta e salva o texto extraído em arquivos .txt na pasta de saída."""
    # Cria a pasta de saída se ela não existir
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    for filename in os.listdir(folder_path):
        if filename.endswith(".pdf"):
            pdf_path = os.path.join(folder_path, filename)
            text = extract_text_from_pdf(pdf_path)

            if text:
                # Define o caminho do arquivo de saída de texto
                output_filename = f"{os.path.splitext(filename)[0]}.txt"
                output_path = os.path.join(output_folder, output_filename)
                
                # Salva o texto extraído em um arquivo .txt
                with open(output_path, "w", encoding="utf-8") as f:
                    f.write(text)
                print(f"Texto extraído salvo em {output_path}")

# Caminho para a pasta com os PDFs
folder_path = "Artigos"
# Caminho para a pasta de saída dos textos extraídos
output_folder = "textos_extraidos"

process_pdfs_in_folder(folder_path, output_folder)
