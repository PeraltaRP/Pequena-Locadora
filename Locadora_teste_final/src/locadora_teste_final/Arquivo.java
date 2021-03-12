package locadora_teste_final;

import java.io.*;
import java.text.*;
import java.util.*;

public class Arquivo {

    public void Grava_arquivo_clientes(List<Cliente> lista_clientes) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("clientes.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        String escritor;
        BufferedWriter bw = new BufferedWriter(osw);
        for (int i = 0; i < lista_clientes.size(); i++) {

            if (lista_clientes.get(i).getCpf().contains("/")) {

                escritor = lista_clientes.get(i).getCpf();
                bw.write(escritor);
                bw.newLine();

                escritor = lista_clientes.get(i).getNome();
                bw.write(escritor);
                bw.newLine();

                Cliente_juridico jp = (Cliente_juridico) lista_clientes.get(i);
        
                escritor = jp.getRazao_social();
                bw.write(escritor);
                bw.newLine();

                escritor = lista_clientes.get(i).getEndereco();
                bw.write(escritor);
                bw.newLine();

                escritor = lista_clientes.get(i).getTelefone();
                bw.write(escritor);
                bw.newLine();

                escritor = "" + lista_clientes.get(i).getDivida(); //convertendo tipos numericos para String
                bw.write(escritor);
                bw.newLine();
                
                escritor = "" + lista_clientes.get(i).getStatus(); //convertendo tipos numericos para String
                bw.write(escritor);
                bw.newLine();
                
                escritor = lista_clientes.get(i).getCpf();
            }
            else{
                escritor = lista_clientes.get(i).getCpf();
                bw.write(escritor);
                bw.newLine();

                escritor = lista_clientes.get(i).getNome();
                bw.write(escritor);
                bw.newLine();

                escritor = lista_clientes.get(i).getEndereco();
                bw.write(escritor);
                bw.newLine();

                escritor = lista_clientes.get(i).getTelefone();
                bw.write(escritor);
                bw.newLine();

                escritor = "" + lista_clientes.get(i).getDivida(); //convertendo tipos numericos para String
                bw.write(escritor);
                bw.newLine();
                
                escritor = "" + lista_clientes.get(i).getStatus(); //convertendo tipos numericos para String
                bw.write(escritor);
                bw.newLine();
            }

        }
        bw.close();
    }

    public void Grava_arquivo_carro(List<Carro> lista_carros) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("carros.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        String escritor;
        BufferedWriter bw = new BufferedWriter(osw);
        for (int i = 0; i < lista_carros.size(); i++) {

            escritor = lista_carros.get(i).getPlaca();
            bw.write(escritor);
            bw.newLine();

            escritor = lista_carros.get(i).getModelo();
            bw.write(escritor);
            bw.newLine();

            escritor = lista_carros.get(i).getAno();
            bw.write(escritor);
            bw.newLine();

            escritor = lista_carros.get(i).getDescricao();
            bw.write(escritor);
            bw.newLine();

            escritor = "" + lista_carros.get(i).getKm_atual();
            bw.write(escritor);
            bw.newLine();

            escritor = lista_carros.get(i).getObservacao();
            bw.write(escritor);
            bw.newLine();

            escritor = "" + lista_carros.get(i).getTaxa_km();
            bw.write(escritor);
            bw.newLine();

            escritor = "" + lista_carros.get(i).getTaxa_dia();
            bw.write(escritor);
            bw.newLine();

            escritor = "" + lista_carros.get(i).getSituacao();
            bw.write(escritor);
            bw.newLine();
        }
        bw.close();
    }

    public void Grava_arquivo_alugueis(List<Aluguel> lista_aluguel) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("alugueis.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        String escritor;

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  //formatando o tipo que a data sera salva
        BufferedWriter bw = new BufferedWriter(osw);

        for (int i = 0; i < lista_aluguel.size(); i++) {
            escritor = lista_aluguel.get(i).getCpf_locatario();
            bw.write(escritor);
            bw.newLine();

            escritor = lista_aluguel.get(i).getPlaca_carro();
            bw.write(escritor);
            bw.newLine();

            escritor = lista_aluguel.get(i).getCod_locacao();
            bw.write(escritor);
            bw.newLine();

            escritor = formato.format(lista_aluguel.get(i).getData_inicio()); //formatando a data para String
            bw.write(escritor);
            bw.newLine();

            escritor = formato.format(lista_aluguel.get(i).getData_fim());
            bw.write(escritor);
            bw.newLine();

            escritor = "" + lista_aluguel.get(i).getKm_atual();
            bw.write(escritor);
            bw.newLine();

            escritor = "" + lista_aluguel.get(i).getValor_taxa();
            bw.write(escritor);
            bw.newLine();
        }
        bw.close();
    }

    public List<Aluguel> le_arquivo_aluguel() throws ParseException {
        List<Aluguel> lista_aux = new ArrayList<>();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); //Formatar a String para Data
        String linha;

        float convert_float;
        int convert_int = 0;
        Date convert_data;  //variavel que ira resceber a data do arquivo e jogar na lista

        try {
            InputStream is = new FileInputStream("alugueis.txt");
            InputStreamReader isr = new InputStreamReader(is);
            try (BufferedReader reader = new BufferedReader(isr)) {
                linha = reader.readLine();
                while (linha != null) {
                    Aluguel aux = new Aluguel();
                    aux.setCpf_locatario(linha);
                    
                    linha = reader.readLine();
                    aux.setPlaca_carro(linha);
                    

                    linha = reader.readLine();
                    aux.setCod_locacao(linha);
                   

                    linha = reader.readLine();
                    convert_data = formato.parse(linha);    //convertendo a String para Date
                    aux.setData_inicio(convert_data);

                    linha = reader.readLine();
                    convert_data = formato.parse(linha);
                    aux.setData_fim(convert_data);

                    linha = reader.readLine();
                    convert_int = Integer.parseInt(linha);  //convertendo String para Inteiro
                    aux.setKm_atual(convert_int);

                    linha = reader.readLine();
                    convert_float = Float.parseFloat(linha);    //convertendo String para Float
                    aux.setValor_taxa(convert_float);

                    linha = reader.readLine();

                    lista_aux.add(aux);
                }
                isr.close();
                is.close();
            }
        }
        catch (IOException e) {
            }
        return lista_aux;
    }

    public List<Carro> le_arquivo_carro() throws FileNotFoundException, IOException {

        List<Carro> lista_aux = new ArrayList<>();
        String linha;
        float convert_float;
        int convert_int = 0;

        try {
            InputStream is = new FileInputStream("carros.txt");
            InputStreamReader isr = new InputStreamReader(is);
            try (BufferedReader reader = new BufferedReader(isr)) {
                linha = reader.readLine();
                while (linha != null) {
                    Carro aux = new Carro();

                    aux.setPlaca(linha);

                    linha = reader.readLine();
                    aux.setModelo(linha);

                    linha = reader.readLine();
                    aux.setAno(linha);

                    linha = reader.readLine();
                    aux.setDescricao(linha);

                    linha = reader.readLine();
                    convert_int = Integer.parseInt(linha);
                    aux.setKm_atual(convert_int);

                    linha = reader.readLine();
                    aux.setObservacao(linha);

                    linha = reader.readLine();
                    convert_float = Float.parseFloat(linha);
                    aux.setTaxa_km(convert_float);

                    linha = reader.readLine();
                    convert_float = Float.parseFloat(linha);
                    aux.setTaxa_dia(convert_float);

                    linha = reader.readLine();
                    convert_int = Integer.parseInt(linha);
                    aux.setSituacao(convert_int);

                    linha = reader.readLine();
                    lista_aux.add(aux);
                }
            
            }
        } catch (IOException e) {
        
        }
        return lista_aux;
    }

    public List<Cliente> le_arquivo_cliente() throws FileNotFoundException {
        List<Cliente> lista_aux = new ArrayList<>();

        String linha;

        try {
            InputStream is = new FileInputStream("clientes.txt");
            InputStreamReader isr = new InputStreamReader(is);
            try (BufferedReader reader = new BufferedReader(isr)) {
                linha = reader.readLine(); //le a primeira linha do arquivo, esta linha devera conter o CPF/CNPJ
                while (linha != null) {
                    if (linha.contains("/")) {
                        Cliente aux_juridico = new Cliente_juridico();
                        aux_juridico.setCpf(linha);

                        linha = reader.readLine();
                        aux_juridico.setNome(linha);

                        linha = reader.readLine();
                        ((Cliente_juridico) aux_juridico).setRazao_social(linha);

                        linha = reader.readLine();
                        aux_juridico.setEndereco(linha);

                        linha = reader.readLine();
                        aux_juridico.setTelefone(linha);
                        
                        linha = reader.readLine();
                        aux_juridico.setDivida(Float.parseFloat(linha));
                        
                        linha = reader.readLine();
                        aux_juridico.setStatus(Integer.parseInt(linha));
                        
                        lista_aux.add(aux_juridico);
                        
                        linha = reader.readLine();
                        
                    }
                    else {
                        Cliente aux = new Cliente();

                        aux.setCpf(linha);

                        linha = reader.readLine();
                        aux.setNome(linha);

                        linha = reader.readLine();
                        aux.setEndereco(linha);

                        linha = reader.readLine();
                        aux.setTelefone(linha);

                        
                        linha = reader.readLine();
                        aux.setDivida(Float.parseFloat(linha));
                        
                        linha = reader.readLine();
                        aux.setStatus(Integer.parseInt(linha));
                        
                        lista_aux.add(aux);
                        linha = reader.readLine();
                    }
                }

            } catch (IOException e) {
                
            }
        } catch (IOException e) {
           
        }

        return lista_aux;
    }
    
}
