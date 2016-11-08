package br.com.levimendes.teste.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by 809778 on 04/07/2016.
 */
public class User implements Serializable {

    @SerializedName("first_name")
    public String nome;
    @SerializedName("last_name")
    public String sobrenome;
    @SerializedName("email")
    public String email;
    public String urlPicture;
    @SerializedName("birthday")
    public String dataNascimento;
    @SerializedName("location")
    public String location;
    @SerializedName("id")
    public String id;
    @SerializedName("gender")
    public String sexo;

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
