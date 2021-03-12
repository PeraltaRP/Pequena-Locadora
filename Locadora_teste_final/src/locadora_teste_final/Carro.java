package locadora_teste_final;

import java.util.Scanner;

public class Carro {
    private String placa;
    private String modelo;
    private String descricao;
    private String ano;
    private String observacao;
    private int km_atual;
    private float taxa_km;
    private float taxa_dia;
    private int situacao;
    
    public Carro(){
        this.placa = "";
        this.modelo = "";
        this.descricao = "";
        this.ano = "";
        this.observacao = "";
        this.km_atual = 0;
        this.taxa_km = 0;
        this.taxa_dia = 0;
        this.situacao = 0;
        
    }
    
    public Carro (String Aplaca, String Amodelo, String Adescricao, String Aano, String Aobsercao, int Akm_atual, float Ataxa_km, float Ataxa_dia){
        this.setPlaca(Aplaca);
        this.setModelo(Amodelo);
        this.setDescricao(Adescricao);
        this.setAno(Aano);
        this.setObservacao(Aobsercao);
        this.setKm_atual(Akm_atual);
        this.setTaxa_km(Ataxa_km);
        this.setTaxa_dia(Ataxa_dia);
        this.setSituacao(0);
    }

    public String getPlaca() { return placa;   }
    public void setPlaca(String placa) {   this.placa = placa;    }
    public String getModelo() {  return modelo;    }
    public void setModelo(String modelo) {  this.modelo = modelo;    }
    public String getDescricao() {  return descricao;    }
    public void setDescricao(String descricao) {   this.descricao = descricao;    }
    public String getAno() {     return ano;    }
    public void setAno(String ano) {    this.ano = ano;    }
    public String getObservacao() {   return observacao;    }
    public void setObservacao(String observacao) {        this.observacao = observacao;    }
    public int getKm_atual() {        return km_atual;    }
    public void setKm_atual(int km_atual) {        this.km_atual = km_atual;    }
    public float getTaxa_km() {        return taxa_km;    }
    public void setTaxa_km(float taxa_km) {        this.taxa_km = taxa_km;    }
    public float getTaxa_dia() {        return taxa_dia;    }
    public void setTaxa_dia(float taxa_dia) {        this.taxa_dia = taxa_dia;    }
    public int getSituacao() {        return situacao;    }
    public void setSituacao(int situacao) {        this.situacao = situacao;    }
            
    
}
