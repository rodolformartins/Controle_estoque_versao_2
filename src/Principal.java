import java.util.ArrayList;
import java.util.Scanner;






public class Principal {

    ArrayList<Mercadorias> prodList = new ArrayList<Mercadorias>();
    private int posicaoAtual = 0;


    //Chamando o main
    public static void main(String[] args)   {
        Principal app = new Principal();
        app.menuPrincipal();

    }

    Scanner scan = new Scanner(System.in);

    private void tituloPrincipal(){


        System.out.println("\nEMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.\nSISTEMA DE CONTROLE DE ESTOQUE");
    }


    //MENU PRINCIPAL
    private void menuPrincipal(){
        tituloPrincipal();
        int opcao = 0;
        System.out.print("\nMENU PRINCIPAL\n1 - CADASTRO DE PRODUTOS\n2 - MOVIMENTAÇÃO\n3 - REAJUSTE DE PREÇOS\n4 - RELATÓRIOS\n0 - FINALIZAR\nOPÇÃO:_ ");
        do {
            opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    menuMovimentacao();
                case 3:
                    reajustePreco();
                    break;
                case 4:
                    relatorioDeProdutos();
                    break;
                case 0:
                    System.out.println("Sistema encerrado com sucesso!");
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        } while (opcao != 0);

    }







    //MENU - CADASTRO DE PRODUTOS
    private void adicionarProduto()  {
        int opcao;
        tituloPrincipal();
        System.out.println("**** CADASTRO DE PRODUTOS ****");
        System.out.print("\n1 - INCLUSÃO\n" +
                "2 - ALTERAÇÃO\n" +
                "3 - CONSULTA\n" +
                "4 - EXCLUSÃO\n" +
                "0 - RETORNAR\nOPÇÃO: ");
        opcao = getEscolhaMenu();
        switch (opcao) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                alterarProduto();
                break;
            case 3:
                consultarProduto();
                break;
            case 4:
                excluirProduto();
                break;
            case 0:
                menuPrincipal();
            default:
                opcaoInvalida();
                break;
        }

    }


		/* if(x.getAtributo().equal( y.getAtributo() )){
		faz alguma coisa
		} */




    //MENU - CADASTRAR PRODUTOS
    private void cadastrarProdutos() {
        String escolha;
        do {
            tituloPrincipal();

            System.out.println("**** INCLUSÃO DE PRODUTOS ****");
            Mercadorias mercadoria = setDadosDoProduto();
            escolha = confirmaOperacao();
            if (escolha.equalsIgnoreCase("S")) {
                if(verificaDuplicidade(mercadoria)) {
                    System.out.println("Esse produto já foi cadastrado!");
                }
                else {
                    prodList.add(mercadoria);
                }
            }

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
        adicionarProduto();
    }



    //MÉTODO PARA VERIFICAR SE EXISTE DUPLICIDADE
    private  boolean verificaDuplicidade(Mercadorias mercadoria) {
        boolean jaExiste = false;
        for (Mercadorias item : prodList) {
            if(mercadoria.getNome().equals(item.getNome())) {
                jaExiste = true;
            }
        }
        return jaExiste;

    }






    private String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.print("CONFIRMA ALTERACAO ( S/N )?_");
        escolha = scanner.next();
        return escolha;
    }


    private String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.print("\nREPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
    }


    private int getEscolhaMenu() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }

    private Mercadorias setDadosDoProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInforme o nome do produto");
        String nome = scanner.nextLine();
        System.out.println("Informe a unidade de medida");
        scanner = new Scanner(System.in);
        String unidade = scanner.nextLine();
        System.out.println("Informe a quantidade");
        float quantidade = scanner.nextFloat();
        System.out.println("Informe o preço");
        float preco = scanner.nextFloat();

        Mercadorias mercadoria = new Mercadorias();
        mercadoria.setNome(nome);
        mercadoria.setUnidade(unidade);
        mercadoria.setPreco(preco);
        mercadoria.setQuantidade(quantidade);
        return mercadoria;
    }


    private void relatorioDeProdutos()  {
        tituloPrincipal();
        System.out.println("RELATÓRIO");
        for (int i = 0; i < prodList.size(); i++) {
            if(prodList.get(i) == null) {
                System.out.println("Vazio!");
            }
            else {
                System.out.println("\n");
                System.out.println("Produtos: \n" + "Código: " +
                        i + "\n" +
                        prodList.get(i));
            }

        }
        menuPrincipal();

    }

    private void opcaoInvalida() {
        System.out.println("Opção inválida");
    }



    private void mensagemConsultaNaoEncontrada(boolean controle) {
        if (controle) {
            System.out.println("Produto não encontrado");
        }
    }


    private void alterarProduto() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            tituloPrincipal();
            System.out.println("ALTERAÇÃO DE PRODUTO");
            System.out.println("Informe o nome do produto para alterar");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < prodList.size(); i++) {

                if (nomeConsulta.equalsIgnoreCase(prodList.get(i).getNome())) {
                    controle=false;
                    System.out.println("PRODUTO ENCONTRADO\n");
                    Mercadorias mercadoria = setDadosDoProduto();
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        prodList.add(mercadoria);
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
        //Chama de volta o menu superior
        adicionarProduto();
    }



    //CONSULTAR PRODUTOS
    private void consultarProduto() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            tituloPrincipal();
            System.out.println("CONSULTA DE PRODUTOS");
            System.out.println("Informe o nome do produto para pesquisa");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i <= prodList.size() ; i++) {
                if (nomeConsulta.equalsIgnoreCase(prodList.get(i).getNome())) {
                    controle=false;
                    System.out.println(prodList.get(i).toString());
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
        //Chama de volta o menu superior
        adicionarProduto();
    }


    private void excluirProduto() {

        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            tituloPrincipal();
            System.out.println("EXCLUSÃO DE PRODUTOS");
            System.out.println("Informe o nome do produto para pesquisa");
            String nomeConsulta = scanner.nextLine();
            boolean controle = true;
            for (int i = 0; i <= prodList.size(); i++) {
                if (nomeConsulta.equalsIgnoreCase(prodList.get(i).getNome())) {
                    controle=false;
                    System.out.println(prodList.get(i).toString());
                    System.out.println("CONFIRMA EXCLUSÃO ( S/N ) ?");
                    escolha = scanner.next();
                    if (escolha.equalsIgnoreCase("S")) {
                        prodList.remove(i);
                    }

                    break;
                }


            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        } while (escolha.equalsIgnoreCase("S"));

        adicionarProduto();
    }




    //MENU MOVIMENTACAO DE PRODUTO
    private void menuMovimentacao() {
        Scanner scanner = new Scanner(System.in);
        tituloPrincipal();
        System.out.println("MOVIMENTAÇÃO");
        System.out.println("1 - ENTRADA\n" +
                "2 - SAÍDA\n" +
                "0 - RETORNAR\n" +
                "OPÇÃO  : \n");
        int opcaoMovimentacao = scanner.nextInt();
        switch (opcaoMovimentacao) {
            case 1:
                compraProdutos();
                break;
            case 2:
                saidaProdutos();
                break;
            case 0:
                System.out.println("Retornando para o menu");
                menuPrincipal();
                break;
            default:
                opcaoInvalida();
                break;
        }
    }


    //FUNCAO PARA COMPRAR PRODUTOS
    private void compraProdutos() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("COMPRA DE PRODUTOS");
            System.out.println("Nome do produto");
            String nomeMantimento = scanner.nextLine();
            Mercadorias produtosMovimentacao;
            boolean controle=true;
            for (int i = 0; i < prodList.size(); i++) {
                if (nomeMantimento.equalsIgnoreCase(prodList.get(i).getNome())) {
                    controle=false;
                    produtosMovimentacao = prodList.get(i);
                    System.out.println("QTDE ATUAL : " + produtosMovimentacao.getQuantidade());
                    System.out.println("QTDE ENTRADA : ");
                    int quantidadeEntrada = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtosMovimentacao.getQuantidade() + quantidadeEntrada));
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setAdicionarQuantidade(quantidadeEntrada);
                        produtosMovimentacao = prodList.get(i);
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));

        menuMovimentacao();
    }





    //SAIDA DE PRODUTOS
    private void saidaProdutos() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Venda dos produtos");
            System.out.println("Nome do produto");
            String nomeProduto = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < prodList.size(); i++) {
                if (nomeProduto.equalsIgnoreCase(prodList.get(i).getNome())) {
                    controle=false;
                    Mercadorias produtosMovimentacao = prodList.get(i);
                    System.out.println("QTDE ATUAL : " + produtosMovimentacao.getQuantidade());
                    System.out.println("QTDE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtosMovimentacao.getQuantidade() - quantidadeSaida));
                    if (produtosMovimentacao.getQuantidade() < quantidadeSaida) {
                        System.out.println("Quantidade maior que no estoque, saída não é possível");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setDiminuirQuantidade(quantidadeSaida);
                        produtosMovimentacao = prodList.get(i);
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
        menuMovimentacao();
    }


    //FUNCAO PARA REAJUSTAR PRECO
    private void reajustePreco() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            tituloPrincipal();
            System.out.println("REAJUSTE DE PREÇO");
            System.out.println("Nome do produto");
            String nomeProduto = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < prodList.size(); i++) {
                if (nomeProduto.equalsIgnoreCase(prodList.get(i).getNome())) {
                    controle=false;
                    Mercadorias produtosMovimentacao = prodList.get(i);
                    System.out.println("PREÇO ATUAL: " + produtosMovimentacao.getPreco());
                    System.out.println("QUAL PERCENTUAL VOCÊ QUER AUMENTAR? ");
                    float novoPercentual = scanner.nextFloat();
                    System.out.println("PREÇO REAJUSTADO : " + ((produtosMovimentacao.getPreco() * (novoPercentual / 100) + produtosMovimentacao.getPreco())));
                    if (produtosMovimentacao.getPreco() >= ((produtosMovimentacao.getPreco() * (novoPercentual / 100) + produtosMovimentacao.getPreco()))) {
                        System.out.println("Preço do reajuste é menor, não e possivel reajustar!");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        float novoPreco = (((produtosMovimentacao.getPreco() * (novoPercentual / 100)) + produtosMovimentacao.getPreco()));

                        prodList.get(i).setPreco(novoPreco);
                        System.out.println("Imprimindo o valor do indice: " + prodList.get(i).getPreco());

                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
        menuPrincipal();
    }




}