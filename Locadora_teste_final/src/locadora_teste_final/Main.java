package locadora_teste_final;
// aqui eu altero este comentario
//asdasdadasd
// sao testes que eu faço pra saber como usar o git


import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Cliente cliente = null;
        Cliente cliente_juridico = null;
        Carro carro = new Carro();
        Aluguel aluguel = new Aluguel();
        Arquivo arquivo = new Arquivo();
        
        List<Cliente> lista_clientes = new ArrayList<>();
        List<Aluguel> lista_aluguel = new ArrayList<>();
        List<Carro> lista_carros = new ArrayList<>();

        //ao iniciar populamos a lista com os dados dos arquivos e jogando nas listas
        lista_carros.addAll(arquivo.le_arquivo_carro());
        lista_clientes.addAll(arquivo.le_arquivo_cliente());
        lista_aluguel.addAll(arquivo.le_arquivo_aluguel());

        boolean sistema = true;
        int opcao, pos_cliente = 0;
        Scanner entrada = new Scanner(System.in);
       
        
       do {
            System.out.println("1 - Clientes");
            System.out.println("2 - Carros");
            System.out.println("3 - Alugueis");
            System.out.println("4 - Relatorios");
            System.out.println("5 - Sair");
            opcao = entrada.nextInt();
            entrada = new Scanner(System.in);
            switch (opcao) {
                case 1:
                    System.out.println("1 - Cadastrar Novo Cliente");
                    System.out.println("2 - Remover Cliente");
                    System.out.println("3 - Imprimir Clientes");

                    opcao = entrada.nextInt();
                    entrada = new Scanner(System.in);

                    if (opcao == 1) {
                        cadastra_cliente(lista_clientes);
                    }
                    if (opcao == 2) {
                        imprimir_cliente(lista_clientes, "n");
                        remover_cliente(lista_clientes);
                    }
                    if (opcao == 3) {
                        imprimir_cliente(lista_clientes, "N"); //N para imprimir normalmente
                    }
                    break;

                case 2:
                    System.out.println("1 - Cadastrar Novo Veiculo");
                    System.out.println("2 - Remover Veiculo");
                    System.out.println("3 - Imprimir Veiculos");
                    opcao = entrada.nextInt();
                    entrada = new Scanner(System.in);
                    if (opcao == 1) {
                        cadastra_carro(lista_carros);
                    }
                    if (opcao == 2) {
                        imprime_carro(lista_carros);
                        remove_carro(lista_carros);
                    }
                    if (opcao == 3) {
                        imprime_carro(lista_carros);
                    }
                    break;
                case 3:
                    System.out.println("1 - Locar Veiculo");
                    System.out.println("2 - Devolver Veiculo");
                    opcao = entrada.nextInt();
                    entrada = new Scanner(System.in);
                    if (opcao == 1) {
                        pos_cliente = cliente_divida(lista_clientes);
                        if(pos_cliente >= 0){
                        lista_aluguel.add(aluguel.nova_locacao(lista_aluguel, lista_clientes.get(pos_cliente).getCpf(), lista_carros));
                        }
                        if(pos_cliente == -2){
                            System.out.println("Cliente nao encontrado");
                            pos_cliente = cliente_divida(lista_clientes);
                            lista_aluguel.add(aluguel.nova_locacao(lista_aluguel, lista_clientes.get(pos_cliente).getCpf(), lista_carros));
                        }
                        if(pos_cliente == -1){
                            main(args);
                        }
                    }
                    if (opcao == 2) {
                        aluguel.devolve_carro(lista_aluguel, lista_carros, lista_clientes);
                    }
                    break;
                case 4:
                    System.out.println("1 - Imprimir Clientes com Divida Ativa");
                    System.out.println("2 - Buscar Alugueis Por Periodo");
                    System.out.println("3 - Buscar Por Cliente Especifico");
                    opcao = entrada.nextInt();
                    entrada = new Scanner(System.in);

                    if (opcao == 1) {
                        imprimir_cliente(lista_clientes, "D"); //D para imprimir Clientes com Divida
                    }
                    if (opcao == 2) {
                        busca_por_data(lista_aluguel);
                    }
                    if (opcao == 3) {
                        imprimir_cliente(lista_clientes, "e");
                    }
                    break;
                case 5:
                    sistema = false;
                    break;
                default:
                    System.out.println("Opcao Invalida");
            }

        } while (sistema == true);
 
        //gravando arquivos no disco
        arquivo.Grava_arquivo_carro(lista_carros);
        arquivo.Grava_arquivo_clientes(lista_clientes);
        arquivo.Grava_arquivo_alugueis(lista_aluguel);
     
    }
    //secao dos clientes
    public static List cadastra_cliente(List<Cliente> lista_clientes) {
        Scanner entrada = new Scanner(System.in);
        String cpf, nome, razao_social, endereco, telefone;
        int divida;
        Cliente_juridico cliente_juridico = null;
        Cliente cliente = null;

        System.out.println("Digite o CPF");
        cpf = entrada.nextLine();
        entrada = new Scanner(System.in);

        if (cpf.contains("/")) {

            System.out.println("Digite o Nome");
            nome = entrada.nextLine();
            entrada = new Scanner(System.in);

            System.out.println("Digite a Razao Social");
            razao_social = entrada.nextLine();
            entrada = new Scanner(System.in);

            System.out.println("Digite o Endereco");
            endereco = entrada.nextLine();
            entrada = new Scanner(System.in);

            System.out.println("Digite o telefone");
            telefone = entrada.nextLine();
            entrada = new Scanner(System.in);

            cliente_juridico = new Cliente_juridico(cpf, nome, razao_social, telefone, endereco);
            lista_clientes.add(cliente_juridico);

        } else {
            System.out.println("Digite o Nome");
            nome = entrada.nextLine();
            entrada = new Scanner(System.in);

            System.out.println("Digite o Endereco");
            endereco = entrada.nextLine();
            entrada = new Scanner(System.in);

            System.out.println("Digite o telefone");
            telefone = entrada.nextLine();
            entrada = new Scanner(System.in);
            cliente = new Cliente(cpf, nome, telefone, endereco);
            lista_clientes.add(cliente);
        }
        return lista_clientes;
    }
    //verifica se o cliente não tem divida ativa
    public static int cliente_divida(List <Cliente> lista_clientes){
       int i, opcao;
       float valor_pago;
       String cpf;
       
       Scanner entrada = new Scanner(System.in);
       System.out.println("Digite o Cpf");
       cpf = entrada.nextLine();

       for(i = 0; i < lista_clientes.size(); i++){
           if(cpf.equalsIgnoreCase(lista_clientes.get(i).getCpf())){
               if(lista_clientes.get(i).getDivida() < 1){
                lista_clientes.get(i).setStatus(1);
                 System.out.println("ped");
                return i;
               }
               else{

                System.out.println("Cliente Com Divida Ativa");
                System.out.println("1 - Realizao Quitacao?");
                System.out.println("2 - Cancelar operacao?");
                opcao = entrada.nextInt();
                entrada = new Scanner (System.in);
                
                if(opcao == 1){
                    System.out.println("Valor da Divida Atual: " + lista_clientes.get(i).getDivida());
                    System.out.println("Digite o Total Rescebido: ");
                    valor_pago = entrada.nextInt();
                    valor_pago = lista_clientes.get(i).getDivida() - valor_pago;
                    if(valor_pago == 0){
                        return i;
                    }
                    else{
                        System.out.println("Impossivel Realizar Operacao");
                        System.out.println("Cliente com Divida Ativa");
                        return -1;
                       
                    }
                }
                   
               }
           }
       }
        return -2;
    }
                  

    public static void remover_cliente(List<Cliente> lista_clientes) {
        int i;
        String cpf;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite o CPF");
        cpf = entrada.nextLine();
        for (i = 0; i < lista_clientes.size(); i++) {
            if (cpf.equalsIgnoreCase(lista_clientes.get(i).getCpf()) && lista_clientes.get(i).getDivida() == 0 && lista_clientes.get(i).getStatus() == 0 ) {
                lista_clientes.remove(i);
            }
            else{
                if(lista_clientes.get(i).getDivida() > 0 ){
                    System.out.println("Cliente com Divida Ativa");
                    System.out.println("Impossivel Excluir");
                }
                if(lista_clientes.get(i).getStatus() == 1 ){
                    System.out.println("Cliente com Aluguel Ativo");
                    System.out.println("Impossivel Excluir");
                }
            }
        }
    }
    public static void imprimir_cliente(List<Cliente> lista_clientes, String tipo) {
        int i;
        if(tipo.equalsIgnoreCase("n")){
            System.out.printf("%-30s%-12s\n","Nome", "CPF");
            for (i = 0; i < lista_clientes.size(); i++) {
                System.out.printf("%-30s", lista_clientes.get(i).getNome());
                System.out.printf("%-12s\n", lista_clientes.get(i).getCpf());
            }
            System.out.println("");
        }
        if(tipo.equalsIgnoreCase("d")){
            System.out.printf("%-12s%-12s%-12s\n","Nome", "CPF", "Divida");
            for (i = 0; i < lista_clientes.size(); i++) {
                if(lista_clientes.get(i).getDivida() > 0){
                    System.out.printf("%-12s", lista_clientes.get(i).getNome());
                    System.out.printf("%-12s", lista_clientes.get(i).getCpf());
                    System.out.printf("%-12s\n", lista_clientes.get(i).getDivida());
                }
            }
            System.out.println("");
        }
        if(tipo.equalsIgnoreCase("e")){
            Scanner entrada = new Scanner(System.in);
            System.out.println("Digite o CPF");
            String cpf = entrada.nextLine();
            if(cpf.contains("/")){
                System.out.printf("%-30s%-40s%-40s%-12s\n","Nome","Razao Social", "CPF", "Divida");
                for (i = 0; i < lista_clientes.size(); i++) {
                    if(lista_clientes.get(i).getCpf().equalsIgnoreCase(cpf)){
                        System.out.printf("%-30s", lista_clientes.get(i).getNome());
                        Cliente_juridico jp = (Cliente_juridico) lista_clientes.get(i);
                        String escritor = jp.getRazao_social();
                        System.out.printf("%-40s", escritor);
                        System.out.printf("%-40s", lista_clientes.get(i).getCpf());
                        System.out.printf("%-12s\n", lista_clientes.get(i).getDivida());
                    }
                }
                System.out.println("");
            }
            else{
                System.out.printf("%-12s%-12s%-12s\n","Nome", "CPF", "Divida");
                for (i = 0; i < lista_clientes.size(); i++) {
                    if(lista_clientes.get(i).getCpf().equalsIgnoreCase(cpf)){
                        System.out.printf("%-12s", lista_clientes.get(i).getNome());
                        System.out.printf("%-12s", lista_clientes.get(i).getCpf());
                        System.out.printf("%-12s\n", lista_clientes.get(i).getDivida());
                    }
                }
                System.out.println("");
            }
        }
    
    }

    //secao dos carros
    public static List cadastra_carro(List <Carro> lista_carros){
        Scanner entrada = new Scanner(System.in);
        String placa, modelo, descricao, observacao, ano;
        float taxa_km, taxa_dia;
        int km_atual;
        
        System.out.println("Digite a Placa");
        placa = entrada.nextLine();
        entrada = new Scanner(System.in);
        
        System.out.println("Digite o Modelo");
        modelo = entrada.nextLine();
        entrada = new Scanner(System.in);
        
        System.out.println("Digite a descricao");
        descricao = entrada.nextLine();
        entrada = new Scanner(System.in);
                
        System.out.println("Digite o Ano");
        ano = entrada.nextLine();
        entrada = new Scanner(System.in);
        
        System.out.println("Digite a Observacao");
        observacao = entrada.nextLine();
        entrada = new Scanner(System.in);
        
        System.out.println("Digite o KM Atual");
        km_atual = entrada.nextInt();
        entrada = new Scanner(System.in);
        
        System.out.println("Digite a taxa por KM");
        taxa_km = entrada.nextFloat();
        entrada = new Scanner(System.in);
        
        System.out.println("Digite a taxa por Dia");
        taxa_dia = entrada.nextFloat();
        entrada = new Scanner(System.in);
        
        Carro carro = new Carro(placa, modelo, descricao, ano, observacao, km_atual, taxa_km, taxa_dia);
        lista_carros.add(carro);
        
        return lista_carros;
    } 
    public static void remove_carro(List <Carro> lista_carros){
        int i;
        String placa;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite a Placa do Veiculo");
        placa = entrada.nextLine();
        for (i = 0; i < lista_carros.size(); i++) {
            if (placa.equalsIgnoreCase(lista_carros.get(i).getPlaca())) {
                lista_carros.remove(i);
            }
        }
    }
    public static void imprime_carro(List <Carro> lista_carros){
        int i;
         System.out.printf("%-12s%-40s%-12s%-12s%-12s\n", "PLACA", "MODELO", "KM ATUAL", "TAXA DIA", "TAXA KM");
        for(i = 0; i < lista_carros.size(); i++){
            System.out.printf("%-12s", lista_carros.get(i).getPlaca());
            System.out.printf("%-40s", lista_carros.get(i).getModelo());
            System.out.printf("%-12s", lista_carros.get(i).getKm_atual());
            System.out.printf("%-12s", lista_carros.get(i).getTaxa_km());
            System.out.printf("%-12s", lista_carros.get(i).getTaxa_dia());
            System.out.println("");
        }
        System.out.println("");
    }
    
    //secao dos relatorios
    public static void busca_por_data(List <Aluguel> lista_aluguel) throws ParseException{
        Scanner entrada = new Scanner(System.in);
        int i;
        String periodo_inicial; 
        String periodo_final;
        Date data_inicio ;
        Date data_final;
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYY");
        
        System.out.println("Digite a data inicial ");
        periodo_inicial = entrada.nextLine();
        data_inicio = formato.parse(periodo_inicial);
        
        System.out.println("Digite a data final");
        periodo_final = entrada.nextLine();
        data_final = formato.parse(periodo_final);
        
        System.out.printf("%-20s%-20s%-20s%-12s\n","Codigo Locacao", "Data de Inicio", "Data de Termino", "Tipo de Cobranca");
        for (i = 0; i < lista_aluguel.size(); i++) {
            if (data_inicio.before(lista_aluguel.get(i).getData_inicio()) || periodo_inicial.equals("" + lista_aluguel.get(i).getData_inicio())) {
                if (data_final.before(lista_aluguel.get(i).getData_fim()) || periodo_final.equals("" + lista_aluguel.get(i).getData_fim())) {
                    System.out.printf("%-20s", lista_aluguel.get(i).getCod_locacao());
                    System.out.printf("%-20s", formato.format(lista_aluguel.get(i).getData_inicio()));
                    System.out.printf("%-20s", formato.format(lista_aluguel.get(i).getData_fim()));
                    if (lista_aluguel.get(i).getCod_locacao().substring(0, 1).equalsIgnoreCase("d")) {
                        System.out.printf("%-12s\n", "Diaria");
                    } else {
                        System.out.printf("%-12s\n", "Km Rodado");
                    }
                }
            }
        }
    }
}
