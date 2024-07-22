from transformers import pipeline

# This part is only to make it easier to check models. Will be removed later.
# Would suggest adding a button for the user to decide whether they are uploading an article or meeting minutes.

document_type = input("M or A? ")
if document_type == "M":
    model_name = "knkarthick/meeting-summary-samsum" # This model works better with minutes summarisation
    with open("minutes.txt", "r", encoding="utf-8") as MINUTES:
        text = MINUTES.read()
else:
    model_name = "facebook/bart-large-cnn" # Better for articles.
    with open("article.txt", "r", encoding="utf-8") as ARTICLE:
        text = ARTICLE.read()
#####

summarizer = pipeline("summarization", model=model_name)

def summarize_input(input_text):
    summary = summarizer(input_text, max_length=150, min_length=10) # Specify the length of the output.
    return summary[0]['summary_text']


# text = ARTICLE
summary = summarize_input(text)
print(f"The summary of the text given:\n {summary}")
