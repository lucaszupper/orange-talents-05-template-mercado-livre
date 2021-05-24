package br.com.zup.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {
    @Autowired
    private Mailer mailer;



    public void novaPergunta(PerguntaProduto pergunta){
        
        mailer.send("<html>...</html>","Nova Pergunta..." ,pergunta.getUsuario().getLogin(), "perguntas@zup.com.br" ,pergunta.getProduto().getUsuario().getLogin());
    
    }

}
