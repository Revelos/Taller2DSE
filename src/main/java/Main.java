import hibernateapp.entity.Cliente;
import hibernateapp.services.ClienteService;
import hibernateapp.services.ClienteServiceImpl;
import hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class Main {
    private static Integer opcion = -1;
    private static EntityManager em = JpaUtil.getEntityManager();
    private static  ClienteService cs = new ClienteServiceImpl(em);

    public static void main(String[] args) {
        do {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("BIENVENIDO AL REGISTRO DE CLIENTES\n");
                sb.append("Seleccione una opcion\n");
                sb.append("\t1. Agregar cliente\n");
                sb.append("\t2. Modificar cliente\n");
                sb.append("\t3. Eliminar cliente\n");
                sb.append("\t4. Listar clientes\n");
                sb.append("\t5. Buscar cliente\n");
                sb.append("\t0. Salir\n");
                String opcionStr= JOptionPane.showInputDialog( null, sb, "MENU OPCIONES", JOptionPane.INFORMATION_MESSAGE );
                opcion = Integer.valueOf(opcionStr);
                switch (opcion) {
                    case 1:
                        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado");
                        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del empleado");
                        String edad = JOptionPane.showInputDialog("Ingrese el la edad del empleado");
                        String telefono = JOptionPane.showInputDialog("Ingrese el la telefono del empleado");
                        String email = JOptionPane.showInputDialog("Ingrese el la email del empleado");
                        Cliente cliente = new Cliente(null,nombre,apellido,Integer.valueOf(edad),Integer.valueOf(telefono),email);
                        cs.guardar(cliente);
                        JOptionPane.showMessageDialog(null,"Cliente registrado con exito","Proceso exitoso",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                        String id = JOptionPane.showInputDialog("Ingrese el id del cliente a modificar");
                        Optional<Cliente> response = cs.porId(Long.valueOf(id));
                        if(response.isPresent()){
                            Cliente clienteEdit = response.get();
                            clienteEdit.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del empleado, actual [ "+clienteEdit.getNombre().toUpperCase()+" ]"));
                            clienteEdit.setApellido(JOptionPane.showInputDialog("Ingrese el apellido del empleado, actual [ "+clienteEdit.getApellido().toUpperCase()+" ]" ));
                            clienteEdit.setEdad(Integer.valueOf(JOptionPane.showInputDialog("Ingrese el la edad del empleado, actual [ "+clienteEdit.getEdad()+" ]")));
                            clienteEdit.setTelefono(Integer.valueOf(JOptionPane.showInputDialog("Ingrese el la telefono del empleado, actual [ "+clienteEdit.getTelefono()+" ]")));
                            clienteEdit.setEmail(JOptionPane.showInputDialog("Ingrese el la email del empleado, actual [ "+clienteEdit.getEmail()+" ]"));
                            cs.guardar(clienteEdit);
                            JOptionPane.showMessageDialog(null,"Datos actualizados con exito","Proceso exitoso",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null,"No existe cliente con ese ID","NO DATA",JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case 3:
                        String idDelete = JOptionPane.showInputDialog("Ingrese el id del cliente a eliminar");
                        Optional<Cliente> responses = cs.porId(Long.valueOf(idDelete));
                        if(responses.isPresent()){
                            cs.eliminar(Long.valueOf(idDelete));
                            JOptionPane.showMessageDialog(null,"Cliente eliminado con exito","Proceso exitoso",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                        JOptionPane.showMessageDialog(null,"No existe cliente con ese ID","NO DATA",JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    case 4:
                       List<Cliente>listClient = cs.listar();
                       StringBuilder clientesString = new StringBuilder("LISTA DE CLIENTES\n");
                        listClient.forEach(c->clientesString.append(c.toString()+"\n"));
                        clientesString.append("FIN DE LISTA");
                        JOptionPane.showMessageDialog(null,clientesString,"Lista de clientes",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 5:
                        String ids = JOptionPane.showInputDialog("Ingrese el id del cliente");
                        Optional<Cliente> resp = cs.porId(Long.valueOf(ids));
                        if(resp.isPresent()){
                            JOptionPane.showMessageDialog(null,resp.get().toString(),"Datos de "+resp.get().getNombre(),JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null,"No se encontro cliente con id ="+ids,"NO DATA",JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null,"Hasta pronto!","Saliendo del sistema",JOptionPane.WARNING_MESSAGE);
                    default:
                        System.out.println("\n");
                }
            }catch (InputMismatchException ex){
                JOptionPane.showMessageDialog(null,"Error de entrada de datos!!","Error",JOptionPane.ERROR_MESSAGE);
            }catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,"Verique la entra de datos","Error de entrada",JOptionPane.ERROR_MESSAGE);
            }
        }while (opcion!=0);

    }

}
