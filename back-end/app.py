import gradio as gr
from flask import Flask
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

def greet(name):
    return f"Hello, {name}!"

gr.Interface(fn=greet, inputs="text", outputs="text").launch(share=True)
