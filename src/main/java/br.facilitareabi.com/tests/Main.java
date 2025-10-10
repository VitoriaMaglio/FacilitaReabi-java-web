package br.facilitareabi.com.tests;
import br.facilitareabi.com.controller.ConsultaController;
import br.facilitareabi.com.controller.PacienteController;
import br.facilitareabi.com.controller.UsuarioController;
import br.facilitareabi.com.dao.ConsultaDao;
import br.facilitareabi.com.dao.PacienteDao;
import br.facilitareabi.com.dao.UsuarioDao;
import br.facilitareabi.com.dto.UsuarioRequestDTO;
import br.facilitareabi.com.model.Consulta;
import br.facilitareabi.com.model.Paciente;
import br.facilitareabi.com.model.Usuario;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Consulta consulta = new Consulta();
        ConsultaDao consultaDao = new ConsultaDao();

        ConsultaController consultaController = new ConsultaController();
        Paciente paciente = new Paciente();
        PacienteDao pacienteDao = new PacienteDao();
        PacienteController controller = new PacienteController();
        Paciente pacienteLogado = null;
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();
        UsuarioController usuarioController = new UsuarioController();
        System.out.println("███████╗ █████╗  ██████╗ ██╗██       ██╗████████╗ █████╗       ██████╗  ███████╗  █████╗  ██████╗  ██╗");
        System.out.println("██╔════╝██╔══██╗██╔════╝ ██║██║      ██║╚══██╔══╝██╔══██╗      ██╔══██╗ ██╔════╝ ██╔══██╗ ██   ██╗ ██║");
        System.out.println("█████╗  ███████║██║      ██║██║      ██║   ██║   ███████║      ██████╔╝ █████╗   ███████║ ██████╔╝ ██║");
        System.out.println("██╔══╝  ██╔══██║██║      ██║██║      ██║   ██║   ██╔══██║      ██╔══██╗ ██╔══╝   ██╔══██║ ██   ██╗ ██║ ");
        System.out.println("██║     ██║  ██║╚██████╔╝██║╚██████╔╝██║   ██║   ██║  ██║      ██║  ██║ ████████ ██║  ██║ ███████║ ██║");
        System.out.println("╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝ ╚═════╝ ╚═╝   ╚═╝   ╚═╝  ╚═╝      ╚═╝  ╚═╝ ╚═╝╚═╝╚═╝╚═╝  ╚═╝ ╚═╝  ╚═╝ ╚═╝");
            boolean running = true;
            while (running) {

                System.out.println("\n=== MENU DO SITE ===");
                System.out.println("1 - Login ou Cadastro do paciente");
                System.out.println("2 - Agendar Consulta");
                System.out.println("3 - Remarcar ou Cancelar Consulta");
                System.out.println("4 - Atualizar Paciente");
                System.out.println("5 - Excluir Paciente");
                System.out.println("6 - Atualizar Usuário");
                System.out.println("7 - Excluir Usuario");
                System.out.println("8 - Buscar dados");
                System.out.println("9 - Médico");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine();
                switch (opcao) {
                    case "1":
                        //usuarioController.cadastrarUsuario();
                        System.out.println("\n============================================\n");
                        System.out.println("Agora vamos fazer seu cadastro como paciente para realizar consultas!");
                        pacienteLogado = controller.cadastrarPaciente();
                        System.out.println("\n============================================\n");
                        break;
                    case "2":
                        if (pacienteLogado != null) {
                            consultaController.cadastrarConsulta(pacienteLogado);
                            System.out.println("\n============================================\n");
                        } else {
                            System.out.println(" Você precisa cadastrar/login de um paciente antes de agendar consulta!");
                        }
                        break;
                    case "3":
                        System.out.println("\n============================================\n");
                        if (pacienteLogado != null) {
                            consultaController.remarcarConsulta(consulta, consultaDao, pacienteLogado);
                            System.out.println("\n============================================\n");
                        } else {
                            System.out.println(" Você precisa cadastrar/login de um paciente antes de agendar consulta!");
                        }
                        break;
                    case "4":
                        if (pacienteLogado != null) {
                            controller.atualizarPaciente(paciente);
                            System.out.println("\n============================================\n");
                        } else {
                            System.out.println(" Você precisa cadastrar/login de um paciente antes de agendar consulta!");
                        }
                        break;
                    case "5":
                        if (pacienteLogado != null) {
                            controller.excluirPaciente();
                            System.out.println("\n============================================\n");
                        } else {
                            System.out.println(" Você precisa cadastrar/login de um paciente antes de agendar consulta!");
                        }
                        break;
                    case "6":
                        if (pacienteLogado != null) {

                            //usuarioController.atualizarUsuario();
                            System.out.println("\n============================================\n");
                        } else {
                            System.out.println(" Você precisa cadastrar/login de um paciente antes de agendar consulta!");
                        }
                        break;
                    case "7":
                        if (pacienteLogado != null) {
                            usuarioController.excluirUsuario();
                            System.out.println("\n============================================\n");
                        } else {
                            System.out.println(" Você precisa cadastrar/login de um paciente antes de agendar consulta!");
                        }
                        break;
                    case "8":
                        System.out.println("Buscando dados do sistema...");
                        System.out.println("Você deseja buscar dados de usuários (1), pacientes(2) ou consultas(3)? ");
                        String resp = scanner.nextLine();
                        if (resp.equalsIgnoreCase("1")){
                            usuarioController.buscarUsuario(usuario);
                        } else if (resp.equalsIgnoreCase("2")) {
                            controller.buscarPaciente(pacienteLogado);
                        }else{
                            consultaController.buscarConsulta();
                        }
                        break;
                    case "9":
                        System.out.printf("Você é um médico do sistema?");
                        String respM = scanner.nextLine();
                        if (respM.equalsIgnoreCase("Sim")) {
                            System.out.println("Deseja ver todos os pacientes e consultas do sistema?");
                            String respL = scanner.nextLine();
                            if (respL.equalsIgnoreCase("Sim")) {
                                pacienteDao.listarPacientes();
                                consultaDao.listarConsultas();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                        break;
                    case "0":
                        System.out.println("Digite um feedback para nosso site!");
                        usuarioController.registrarFeedback(usuario);
                        running = false;
                        System.out.println("\n============================================\n");
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }