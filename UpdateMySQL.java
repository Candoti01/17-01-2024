import java.sql.*;
import java.util.*;
public class UpdateMySQL {
    public static void main(String[] args) {
        String status = "Nada aconteceu ainda...";
        Connection conn = App.conectar();
        Scanner scnRespostas = new Scanner(System.in);
        Scanner scnInput = new Scanner(System.in);
        Scanner scnSenha = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Digite o login:");
        String strBusca = scnInput.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Digite sua senha:");
        String scrSenha = scnSenha.nextLine();
        try {
            String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strBusca + "' and `senha` = '" + scrSenha + "' ;";
            Statement stmSql = conn.createStatement();
            ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
            String nome = "" ;            

            if (rsSql.next()) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                status = "Login concluido. Entrando...";
                nome += "[" + rsSql.getString("nome") + "] ";
            } else {
                throw new ArithmeticException("Login ou senha incorretos!");
            }
            System.out.println(status);
            Thread.sleep(2000);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Bem vindo! " + nome);
            System.out.println("\n\nDeseja fazer alguma(s) alteração em seu Nome e/ou senha? [s] ou [n]");
            String strRespostas = scnRespostas.nextLine();
            if (strRespostas.equals("s") || strRespostas.equals("S")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Qual informaçao você deseja alterar digite os numeros para selecionar a opção:\n[1] - Nome\n[2] - Senha\n[3] - Nome & Senha\n[4] - Sair");
                strRespostas = scnRespostas.nextLine();
                switch (strRespostas) {
                    case "1":
                        try {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Login atual: " + strBusca + "\n\n Nome atual: " + nome + "\n\nPara qual nome você deseja alterar?");
                            String strAltNome = scnRespostas.nextLine();
                            String strSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '" + strAltNome
                                    + "' WHERE (`login` = '" + strBusca + "');";
                            stmSql.addBatch(strSqlUpdate);
                            stmSql.executeBatch();
                            stmSql.close();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            status = "Alteração feita com sucesso!";
                        } catch (Exception e) {
                            System.err.println("Algo deu errado " + e);
                        }
                        break;
                    case "2":
                        try {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Login atual: " + strBusca + "\nDigite a senha atual para permissão: ");
                            String strAltSenha = scnRespostas.nextLine();
                            if (strAltSenha.equals(scrSenha)) {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Permição concedida! Iniciando processo...");
                                Thread.sleep(1000);
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Senha atual: " + scrSenha + "\n\nDigite a nova senha! ");
                                strAltSenha = scnRespostas.nextLine();
                                String strSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `senha` = '"
                                        + strAltSenha + "' WHERE (`login` = '" + strBusca + "');";
                                stmSql.addBatch(strSqlUpdate);
                                stmSql.executeBatch();
                                stmSql.close();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                status = "Alteração feita com sucesso!";
                            } else {
                                throw new ArithmeticException("Senha Incorreta!");
                            }
                        } catch (Exception e) {
                            System.err.println("Algo deu errado " + e);
                        }
                        break;
                    case "3":
                        try {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.println("Login atual: " + strBusca + "\n\n Nome atual: " + nome + "\n\nDigite a senha atual para permissão: ");
                            String strAltSenha = scnRespostas.nextLine();
                            if (strAltSenha.equals(scrSenha)) {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Permição concedida! Iniciando processo...");
                                Thread.sleep(1000);
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Digite o novo nome:");
                                String strAltNome = scnRespostas.nextLine();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("Senha atual: " + scrSenha + "\n\nDigite a nova senha: ");
                                strAltSenha = scnRespostas.nextLine();
                                String strSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '"
                                        + strAltNome + "', `senha` = '" + strAltSenha + "' WHERE (`login` = '"
                                        + strBusca + "');";
                                stmSql.addBatch(strSqlUpdate);
                                stmSql.executeBatch();
                                stmSql.close();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                status = "Alteração feita com sucesso!";
                            } else {
                                throw new ArithmeticException("Senha Incorreta!");
                            }
                        } catch (Exception e) {
                            System.err.println("Algo deu errado " + e);
                        }
                        break;
                    case "4":
                        status = "Saindo...";
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                    default:
                        status = "Saindo...";
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                }
            } else {
                status = "Saindo...";
            }
            stmSql.close();
            rsSql.close();
        } catch (Exception e) {
            System.out.println("Ops! Ocorreu o erro" + e);
            status = "Saindo...";
        }
        System.out.println(status);
        scnRespostas.close();
        scnSenha.close();
        scnInput.close();
    }
}