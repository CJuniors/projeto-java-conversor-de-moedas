# 📘 Conversor de Moedas – Java (API ExchangeRate)

Um conversor de moedas simples e eficiente desenvolvido em **Java puro**, sem dependências externas, usando a API pública **ExchangeRate-API**.

Esse projeto oferece **6 opções fixas de conversão**, baseadas em 3 moedas principais:

- **USD (Dólar Americano)**
- **BRL (Real Brasileiro)**
- **EUR (Euro)**

Cada par de moedas possui conversão normal e invertida.

---

## 🔄 Opções disponíveis

1. USD → BRL  
2. BRL → USD  
3. USD → EUR  
4. EUR → USD  
5. BRL → EUR  
6. EUR → BRL  

---

## ⚙️ Como funciona

O projeto consulta a seguinte API pública:
https://v6.exchangerate-api.com/v6/{API_KEY}/latest/{MOEDA_ORIGEM}

Depois extrai a taxa de câmbio desejada usando apenas métodos nativos do Java, sem bibliotecas JSON externas.