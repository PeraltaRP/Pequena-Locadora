from pathlib import Path
import Pyro5.api
import time
import os

user = ""




def listar_arquivos():
    print("arquivos\n")
    adder = Pyro5.api.Proxy("PYRONAME:lista_arquivos")
    x = (adder.lista_arquivos("a")) 
    x = x.split(",")
    for i in range(len(x)):
      
        print(x[i])       


def carrega_arquivo():
    print("Digite o nome do arquivo sem .txt\n")
    nome_arquivo = input()
    caminho = Path(nome_arquivo+".txt").resolve()
    
    try:
        arquivo = open(str(caminho), "r")
        print(arquivo.readlines())
        arquivo.close()
    except IOError:
        print("arquivo inexistente")
    

def grava_arquivo(user):
    print("Digite o nome do arquivo sem .txt\n")
    nome_arquivo = input()
    try:
        arquivo = open(nome_arquivo +".txt", 'a')
        print("Digite sua frase\n")
        frase = input()
        arquivo.writelines(frase)
        arquivo.close()
        
        caminho = Path(nome_arquivo+".txt").resolve()
        adder = Pyro5.api.Proxy("PYRONAME:grava_registros")
        adder.grava_registros((str(caminho)), nome_arquivo,user)
    
    except IOError:
        print("ERRO AO GRAVAR")
    

    
def sair():
    adder = Pyro5.api.Proxy("PYRONAME:grava_regsitro")
    adder.grava_registros("user")
    adder.grava_registros("arq")

def main():
    
    sistema = True
   
    print("Nome de usuario\n")
    user = input()
    
    adder = Pyro5.api.Proxy("PYRONAME:registra_user")
    print(adder.registra_user(user))
    
   
   
   
    while (sistema):
        time.sleep(1);
        
        print("\n")
        print("ls - Listar Arquivos\n")
        print("ca - Carregar Arquivo\n")
        print("ga - Gravar Arquivo\n")
        print("sair - para sair\n")
        
        comando  = input() 
        
        if(comando == "ls"):
            listar_arquivos()
        if(comando == "ga"):
            grava_arquivo(user);
        if(comando == "ca"):
            carrega_arquivo() 
        if(comando == "sair"):
            sair()
            sistema = False   
            

if __name__ == '__main__': # chamada da funcao principal
    
    
    main() # chamada da função main    
        

