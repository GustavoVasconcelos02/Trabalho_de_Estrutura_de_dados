import fitz  # PyMuPDF
import re
import os

def extract_text_from_pdf(pdf_path):
    try:
        doc = fitz.open(pdf_path)
        texto_completo = []
        for page_num in range(len(doc)):
            page = doc.load_page(page_num)
            texto = page.get_text("text")
            texto = clean_text(texto)
            texto_completo.append(texto)
        doc.close()
        return "\n\n".join(texto_completo)
    except Exception as e:
        print(f"Erro ao extrair texto de {pdf_path}: {e}")
        return None

def clean_text(text):
    text = re.sub(r'(?<!\n)\n(?!\n)', ' ', text)
    text = re.sub(r'\n\s*\n', '\n\n', text)
    text = re.sub(r'[ ]+', ' ', text)
    return text

def process_pdfs_in_folder(folder_path, output_folder):
    if not os.path.exists(folder_path):
        print(f"Erro: O diretório {folder_path} não foi encontrado.")
        exit()

    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    files = os.listdir(folder_path)  # Obtém os arquivos sem ordenar
    successful_conversions = 0  # Contador de conversões bem-sucedidas

    for filename in files:
        if filename.endswith(".pdf"):
            pdf_path = os.path.join(folder_path, filename)
            text = extract_text_from_pdf(pdf_path)
            if text:
                output_filename = f"{os.path.splitext(filename)[0]}.txt"
                output_path = os.path.join(output_folder, output_filename)
                with open(output_path, "w", encoding="utf-8") as f:
                    f.write(text)
                print(f"Texto extraído salvo em {output_path}")
                successful_conversions += 1  # Incrementa a contagem

    # Exibe o total de arquivos convertidos com sucesso
    print(f"\nTotal de arquivos convertidos com sucesso: {successful_conversions}")

script_dir = os.path.dirname(os.path.abspath(__file__))
folder_path = os.path.abspath(os.path.join(script_dir, "..", "Artigos"))
output_folder = os.path.abspath(os.path.join(script_dir, "..", "Artigos em txt"))

print(f"Processando PDFs na pasta: {folder_path}")
process_pdfs_in_folder(folder_path, output_folder)