package br.com.zup.mercadolivre.usuario;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String senha;
    private LocalDateTime dataCadastro = LocalDateTime.now();

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = this.criptografarSenha(senha);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    private String criptografarSenha(String senha) {

        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            String senhahex = hexString.toString();

            System.out.println(senhahex);

            return senhahex;


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verificaSenha(String senha){

        String hexSenha = criptografarSenha(senha);
        return hexSenha.equals(this.senha);
    }


}
