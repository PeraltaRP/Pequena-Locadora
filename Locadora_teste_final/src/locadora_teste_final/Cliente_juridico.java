package locadora_teste_final;

public class Cliente_juridico extends Cliente{
    private String razao_social;
    
    public Cliente_juridico (){
        super();
        this.razao_social = "";
    }
    
    public Cliente_juridico(String Acpf, String Anome, String Arazao_social, String Aendereco, String Atelefone){
        super(Acpf,Anome,Aendereco,Atelefone);
        this.setRazao_social(Arazao_social);
    }
    
    public String getRazao_social() { return razao_social;}
    public void setRazao_social(String razao_social) { this.razao_social = razao_social;}
   
}
