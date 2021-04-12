

from pathlib import Path


def test():
    print("digite o nome do arquivo\n")
    arquivo = input()
    caminho = Path(arquivo+".txt").resolve()
    return caminho

def grava(lista):
    arquivo = open("lista.txt", "a")
    for i in range(len(lista)):
        arquivo.writelines(lista[i]) 
    arquivo.close()
    
def main():
    a = ""
    lista = list()
    a = test()
    lista.append(str(a))    
    grava(lista)

if __name__ == '__main__': # chamada da funcao principal
    
    
    main() # chamada da função main  