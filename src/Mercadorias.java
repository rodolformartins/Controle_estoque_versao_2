public class Mercadorias {
    //Atributos
    private String nome;
    private float preco;
    private String unidade;
    private float quantidade;




    //Métodos getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }


    //Método para sempre sobrescrever o que for inserido dentro dos métodos.
    @Override
    public String toString() {
        return "NOME: " + nome + "\n" +
                "PREÇO: R$" + preco + "\n" +
                "UNIDADE: " + unidade + "\n" +
                "QUANTIDADE: " + quantidade + unidade;	    }



    //Métodos especiais
    public void setAdicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    public void setDiminuirQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }

}

