package locadora_teste_final;

public class Cliente {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private float divida;
    private int status;
       
    
    
    public Cliente(){
        this.cpf = "";
        this.nome = "";
        this.endereco = "";
        this.telefone = "";
        this.divida = 0;
        this.status= 0;
    }
    
    public Cliente(String Acpf, String Anome, String Aendereco, String Atelefone){
        this.setCpf(Acpf);
        this.setNome(Anome);
        this.setEndereco(Aendereco);
        this.setTelefone(Atelefone);
        this.divida = 0;
        this.status = 0;
    }
    

    public String getCpf() {  return cpf; }
    public void setCpf(String cpf) {  this.cpf = cpf; }
    public String getNome() { return nome;  }
    public void setNome(String nome) {  this.nome = nome;   }
    public String getEndereco() {  return endereco;   }
    public void setEndereco(String endereco) { this.endereco = endereco;    }
    public String getTelefone() {  return telefone; }
    public void setTelefone(String telefone) {   this.telefone = telefone; }
    public float getDivida() {  return divida;   }
    public void setDivida(float divida) {  this.divida = divida;  }
    
    public int getStatus(){   return this.status;   }
    public void setStatus(int status){ this.status = status; }
}
