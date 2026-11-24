package com.exemplo.notasia.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class IaService {

    private static final String SISTEMA =
            "Você é um assistente de estudos. Responda sempre em português do Brasil, "
            + "de forma clara e objetiva.";

    private final ChatClient chatClient;

    public IaService(ChatClient.Builder builder) {
        this.chatClient = builder.defaultSystem(SISTEMA).build();
    }

    public String resumir(String texto) {
        return chatClient.prompt()
                .user("Resuma o texto abaixo em no máximo 3 frases:\n\n" + texto)
                .call()
                .content();
    }

    public String gerarPerguntas(String texto) {
        return chatClient.prompt()
                .user("Crie 3 perguntas de estudo (com a resposta curta de cada uma) "
                        + "com base no texto abaixo. Use o formato:\n"
                        + "1) Pergunta?\nResposta: ...\n\n" + texto)
                .call()
                .content();
    }

    public String perguntar(String texto, String pergunta) {
        return chatClient.prompt()
                .user("Com base APENAS na anotação abaixo, responda a pergunta.\n\n"
                        + "Anotação:\n" + texto + "\n\nPergunta: " + pergunta)
                .call()
                .content();
    }
}
