package locadora_teste_final;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Aluguel {
    private String cod_locacao;
    private String cpf_locatario;
    private String placa_carro;
    private Date data_inicio;
    private Date data_fim;
    private int km_atual ;
    private float valor_taxa;
    
    
    public  Aluguel nova_locacao(List <Aluguel> lista_aluguel,String cpf, List <Carro> lista_carros) throws ParseException{
        Aluguel aux = new Aluguel();
        String vetor_aluguel[] = new String [7] ;
        SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
        
        data_locacao(vetor_aluguel); //retorna a data da locacao
        veiculo_selecionado(lista_carros, vetor_aluguel, lista_aluguel); // retorna a placa do veiculo escolhido;
       
        aux.setCod_locacao(vetor_aluguel[0] + cpf.substring(0,2) + vetor_aluguel[2].substring(0,1));
        aux.setCpf_locatario(cpf);
        aux.setPlaca_carro(vetor_aluguel[2]);
        aux.setData_inicio(formato.parse(vetor_aluguel[3]));
        aux.setData_fim(formato.parse(vetor_aluguel[4]));
        aux.setKm_atual(Integer.parseInt(vetor_aluguel[5]));
        aux.setValor_taxa(Float.parseFloat(vetor_aluguel[6]));
    
        return aux;   
    }
    
    
    public void data_locacao(String [] vetor_aluguel) throws ParseException{
        Scanner entrada = new Scanner(System.in);
        
        String data;
        Date inicio, fim;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
             
        
        System.out.println("Digite a Data Inicial do Aluguel");
        data = entrada.nextLine();
        inicio = formato.parse(data);
        entrada = new Scanner(System.in);
        
        System.out.println("Digite a Data Final do Aluguel");
        data = entrada.nextLine();
        fim = formato.parse(data);
        entrada = new Scanner(System.in);
        
        if (inicio.after(fim) || inicio.equals(fim)) { //verifica se a data é valida
            System.out.println("Data Incorreta digite novamente");
            data_locacao(vetor_aluguel);
        }
        else{
            vetor_aluguel[3] = formato.format(inicio);
            vetor_aluguel[4] = formato.format(fim);
        }
        
    }
    
    public String[] veiculo_selecionado(List<Carro> lista_carros, String[] vetor_aluguel, List<Aluguel> lista_aluguel) throws ParseException { // retorna a placa do veiculo disponiveis
        int i = 0, j, opcao;
        String placa;

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date inicio_nova_locacao = formato.parse(vetor_aluguel[3]);
        Scanner entrada = new Scanner(System.in);
        List <Carro> lista_carro_aux = new ArrayList<>();
        lista_carro_aux.addAll(lista_carros);
        
        while(i < lista_aluguel.size()){
            for(j = 0; j < lista_carro_aux.size(); j++){
                if (inicio_nova_locacao.before(lista_aluguel.get(i).getData_fim()) && lista_carro_aux.get(j).getPlaca().equalsIgnoreCase(lista_aluguel.get(i).getPlaca_carro())) {
                    lista_carro_aux.get(j).setSituacao(1);
                }
            }
            i++;
        }
      System.out.printf("%-12s%-20s%-12s%-12s%-12s\n", "PLACA", "MODELO", "KM ATUAL", "TAXA DIA", "TAXA KM");
        for(i = 0; i < lista_carros.size(); i++){
            if(lista_carro_aux.get(i).getSituacao() == 0){
                System.out.printf("%-12s", lista_carro_aux.get(i).getPlaca());
                System.out.printf("%-20.17s", lista_carro_aux.get(i).getModelo());
                System.out.printf("%-12s", lista_carro_aux.get(i).getKm_atual());
                System.out.printf("%-12s", lista_carro_aux.get(i).getTaxa_km());
                System.out.printf("%-12s", lista_carro_aux.get(i).getTaxa_dia());
                System.out.println("");
            }
        }
        
        System.out.println("");
        System.out.println("Digite a Placa do carro selecionado");
        placa = entrada.nextLine();
        entrada = new Scanner(System.in);

        
        for (i = 0; i < lista_carros.size(); i++) {
            if (lista_carros.get(i).getPlaca().equalsIgnoreCase(placa)) {
                vetor_aluguel[2] = lista_carros.get(i).getPlaca();
                System.out.println("1 - Cobrar por Diaria?");
                System.out.println("2 - Cobrar por KM_Rodado?");
                opcao = entrada.nextInt();
                entrada = new Scanner(System.in);
                
                if (opcao == 1) {
                    vetor_aluguel[0] = "D";
                } 
                else {
                    vetor_aluguel[0] = "k";
                }
                
                vetor_aluguel[2] = lista_carros.get(i).getPlaca();
                vetor_aluguel[5] = "" + lista_carros.get(i).getKm_atual();
                if (vetor_aluguel[0].equalsIgnoreCase("D")) {
                    
                    vetor_aluguel[6] = "" + lista_carros.get(i).getTaxa_dia();
                }
                else {
                    vetor_aluguel[6] = "" + lista_carros.get(i).getTaxa_km();
                } 
               return vetor_aluguel;
            }
       }
       System.out.println("Veiculo não encontrado");
       veiculo_selecionado(lista_carros, vetor_aluguel, lista_aluguel);
        return null;
       
    
      
    }
 
    
    public void devolve_carro(List<Aluguel> lista_aluguel,List<Carro> lista_carros,List<Cliente> lista_clientes) throws ParseException{
        int i, pos_aluguel = -1, pos_cliente = -1,pos_carro = -1, km_devolucao;
        String codigo;
        String data_devolucao;
        Cliente cliente_aux = new Cliente();
        float valor_cobranca;
        Date data_rescebimento;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        Scanner entrada = new Scanner (System.in);
        
         System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n","Codigo Locacao","Cliente", "Data de Inicio", "Data de Termino", "Tipo de Cobranca","Taxa");
        for (i = 0; i < lista_aluguel.size(); i++) {
            System.out.printf("%-20s", lista_aluguel.get(i).getCod_locacao());
            System.out.printf("%-20s", lista_aluguel.get(i).getCpf_locatario());
            System.out.printf("%-20s", formato.format(lista_aluguel.get(i).getData_inicio()));
            System.out.printf("%-20s", formato.format(lista_aluguel.get(i).getData_fim()));
            if (lista_aluguel.get(i).getCod_locacao().substring(0, 1).equalsIgnoreCase("d")) {
                System.out.printf("%-20s", "Diaria");
                System.out.printf("%-12.2f\n", lista_aluguel.get(i).getValor_taxa());
            }
            else {
                System.out.printf("%-20s", "Km Rodado");
                System.out.printf("%-12.2f\n", lista_aluguel.get(i).getValor_taxa());
            }
        }
    
        System.out.println("");  
        System.out.println("Digite o Codigo do Aluguel");
        codigo = entrada.nextLine();
        
        for(i = 0; i < lista_aluguel.size(); i++){
            if(codigo.equalsIgnoreCase(lista_aluguel.get(i).getCod_locacao())){
                pos_aluguel = i;
            }
            if(lista_aluguel.get(i).getCpf_locatario().equalsIgnoreCase(lista_clientes.get(i).getCpf())){
                pos_cliente = i;
            }
            if(lista_carros.get(i).getPlaca().equalsIgnoreCase(lista_aluguel.get(i).getPlaca_carro())){
                pos_carro = i;
            }
        }
        
        entrada = new Scanner(System.in);
        System.out.println("Digite a data de rescebimento");
        data_devolucao = entrada.nextLine();
        data_rescebimento= formato.parse(data_devolucao);
        
        System.out.println("Digite o KM atual");
        km_devolucao = entrada.nextInt();
        lista_carros.get(pos_carro).setKm_atual(km_devolucao);
        
        if(lista_aluguel.get(pos_aluguel).getCod_locacao().equalsIgnoreCase(codigo)){
            
            if(lista_aluguel.get(pos_aluguel).getCod_locacao().substring(0,1).equalsIgnoreCase("k")){
                valor_cobranca = (km_devolucao - lista_aluguel.get(pos_aluguel).getKm_atual()) * lista_aluguel.get(pos_aluguel).getValor_taxa();
                System.out.printf("Valor a Ser Pago: %.2f\n" , valor_cobranca);
                emite_cobranca(valor_cobranca, pos_cliente, lista_clientes);
            }
            else{
                if(lista_aluguel.get(pos_aluguel).getCod_locacao().substring(0,1).equalsIgnoreCase("D")){
                    long cont_dias = (lista_aluguel.get(pos_aluguel).getData_inicio().getTime() - data_rescebimento.getTime()) + 3600000;
                    cont_dias = ((cont_dias / 86400000L)) * -1; //converte os milessegundos em dias e colocando eles em positivo
                    float total_diaria = cont_dias * lista_aluguel.get(pos_aluguel).getValor_taxa();
                    System.out.println("Total a Pagar: " + total_diaria);
                    emite_cobranca(total_diaria, pos_cliente, lista_clientes);
                }
            }
        }
    }
    public void emite_cobranca(float valor_cobranca, int pos_cliente, List<Cliente> lista_clientes){
        float valor_pago;
        Scanner entrada = new Scanner (System.in);
        
        System.out.println("Total Pago: ");
        valor_pago = entrada.nextFloat();
        
        lista_clientes.get(pos_cliente).setDivida(valor_cobranca - valor_pago );
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String getCod_locacao() {                return cod_locacao;}
    public void setCod_locacao(String cod_locacao) {this.cod_locacao = cod_locacao;    }
    public String getCpf_locatario() {              return cpf_locatario;    }
    public void setCpf_locatario(String cpf_locatario) { this.cpf_locatario = cpf_locatario;    }
    public String getPlaca_carro() {                return placa_carro;    }
    public void setPlaca_carro(String placa_carro) {this.placa_carro = placa_carro;    }
    public Date getData_inicio() {                  return data_inicio;    }
    public void setData_inicio(Date data_inicio) {  this.data_inicio = data_inicio;    }
    public Date getData_fim() {                     return data_fim;    }
    public void setData_fim(Date data_fim) { this.data_fim = data_fim;    }
    public int getKm_atual() {                      return km_atual;    }
    public void setKm_atual(int km_atual) {  this.km_atual = km_atual;    }
    public float getValor_taxa() {                  return valor_taxa;   }
    public void setValor_taxa(float valor_taxa) { this.valor_taxa = valor_taxa; }
    
}
