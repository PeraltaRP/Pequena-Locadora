
import Pyro5.api
import os
from pathlib import Path

@Pyro5.api.expose



class Adder(object):

    def lista_arquivos(self, a):
        arquivo = open("registros.txt", "r")
        lista =[]
        # print("arquivos\n")
        linha = arquivo.readline()
        while linha:
            valores = linha.split(",")
            lista.append(valores[0])
            # print (valores[0])
            linha = arquivo.readline()
        return "{}". format(lista)
        
        arquivo.close()
        
    def registra_user(self, name):
        arquivo = open("usuarios.txt", "a")
        arquivo.write(name)
        arquivo.write("\n")
        arquivo.close()
        return "Usuario Registrado: {}".format(name)

        
    def grava_registros(self, caminho, nome_arquivo, user):
        arquivo = open("registros.txt", "a")
        arquivo.write(nome_arquivo)
        arquivo.write(" ")
        arquivo.write(caminho)
        arquivo.write(" ")
        arquivo.write(user)
        arquivo.write("\n")
        arquivo.close()
    
    

        
        



lista = list ()
lista_arquivos = list()
lista_users = list ()
    
daemon = Pyro5.server.Daemon()         # cria um daemon
ns = Pyro5.api.locate_ns()             # encontra o servidor de nomes
uri = daemon.register(Adder)           # Registra como um objeto Pyro
ns.register("registra_user", uri)
ns.register("diretorio", uri)
ns.register("grava_registros", uri)
ns.register("lista_arquivos", uri)

print("Ready.")
daemon.requestLoop()                   # Começa o loop para esperar chamadas

