package parcial;

import java.util.ArrayList;

import utiles.Lectura;
import utiles.Utiles;

public class LiquidacionEmpresa {


    public static void main(String[] args) {
        ArrayList<NivelAcademico> nivelesAcademicos = new ArrayList<NivelAcademico>();
        addNivelAcademico(10, "Secundario Completo", 10.0, nivelesAcademicos);
        addNivelAcademico(20, "Universitario Completo", 30.0, nivelesAcademicos);
        addNivelAcademico(30, "MaestríaCompleto", 40.0, nivelesAcademicos);
        addNivelAcademico(40, "Doctorando Completo", 50.0, nivelesAcademicos);

        Empresa empresa = new Empresa();
        String cuit;
        String razonSocial;
        System.out.println("------- Ingrese los datos de la empresa -------");
        do{
            cuit = Lectura.leerStringM("ingrese el cuit:");
            if(cuit.length() == 11) break;
            System.out.println("ERROR: el cuit no contiene los digtos suficientes");
        }while(true);
        
        do{
            razonSocial = Lectura.leerStringM("ingrese la razon Social:");
            if(!razonSocial.equals("")) break;
            System.out.println("ERROR: razonSocial vacia");
        }while(true);
        
        empresa.setCuit(cuit);
        empresa.setRazonSocial(razonSocial);

        do {
            System.out.println("------ Ingrese Empleado -----");

            Empleado empleado = new Empleado();
            String nombreEmpleado;
            int legajo;
            double salario;
            NivelAcademico nivelAcademico; 

            do{
                nombreEmpleado = Lectura.leerStringM("nombre del empleado:");
                if(!nombreEmpleado.equals("")) break;
                System.out.println("ERROR: nombre del empleado vacio");
            }while(true);

            do{
                legajo = Lectura.leerIntM("ingrese el legajo:");
                if(!buscarLegajoEmpleado(legajo, empresa)) break;
                System.out.println("El  legajo  ingresado  ya  fue cargado anteriormente");
            }while(true);
            do {
                salario = Lectura.leerDoubleM("ingrese el salario:");
                if(salario > 0) break;
                System.out.println("INCORRECTO: el salaio tiene que ser mayor a 0");
            } while (true);
            
            System.out.println("---- Busqueda del Nivel Academico -----");
            int lugar;
            
            do {
                int codigo = Lectura.leerIntM("ingrese el codigo: ");
                lugar = buscarNivelAcademico(codigo, nivelesAcademicos);
                if(lugar != -1) break;
                System.out.println("El código ingresado no existe, intente nuevamente");
            } while (true);
            
            nivelAcademico = nivelesAcademicos.get(lugar);
            System.out.println("Nivel academico: " + nivelAcademico.getNivel());
            empleado.setLegajo(legajo);
            empleado.setNivelAcademico(nivelAcademico);
            empleado.setNombreCompleto(nombreEmpleado);
            empleado.setSalario(salario);
            empresa.addEmpleado(empleado);
        } while (Utiles.continuarM("¿desea segir añadiendo empeados?"));

        mostrar(empresa);
    }

    public static void mostrar(Empresa empresa) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Empresa:" + empresa.getRazonSocial());
        System.out.println("CUIT:" + empresa.getCuit());
        for (Empleado empleado : empresa.getEmpleados()) {
            System.out.println("------------------------ Empleado ------------------------------");
            
            System.out.println("Nombre:" + empleado.getNombreCompleto());
            System.out.println("Salario: $" + empleado.salarioTotalCalculado());
            System.out.println("Nivel Educativo:" + empleado.getNivelAcademico().getPorcentajeAumento());

        }
        System.out.println("------------------------------------------------------------");

        System.out.println("Resumen:");
        System.out.println("El promedio de los salarios de la empresa es:" + promedioSalarioDeLaEmpresa(empresa));
        System.out.println("El empleado con mejor salario es:");
        Empleado mejorSalario = empleadoConMejorSalario(empresa);
        System.out.println("Nombre Completo:" +  mejorSalario.getNombreCompleto());
        System.out.println("Salario: $" +  mejorSalario.salarioTotalCalculado());
        System.out.println("Nivel Educativo:" +  mejorSalario.getNivelAcademico().getPorcentajeAumento());
        


    }
    public static double promedioSalarioDeLaEmpresa(Empresa empresa) {
        int cantidad= 0;
        double suma = 0;
        for (Empleado empleado : empresa.getEmpleados()) {
            
            suma += empleado.salarioTotalCalculado();
            cantidad ++;
        }
        return suma/cantidad;
    }

    public static Empleado empleadoConMejorSalario(Empresa empresa) {
        Empleado empleadoMayorSalario = empresa.getEmpleados().get(0);
        for (Empleado empleado : empresa.getEmpleados()) {
            
            if(empleado.salarioTotalCalculado() > empleadoMayorSalario.salarioTotalCalculado()){
                empleadoMayorSalario = empleado;
            }
        }
        return empleadoMayorSalario;
    }

    public static int buscarNivelAcademico(int codigo, ArrayList<NivelAcademico> nivelesAcademicos) {
        int i = 0;
        for (NivelAcademico nivelAcademico : nivelesAcademicos) {
            if(codigo == nivelAcademico.getCodigo()){
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean buscarLegajoEmpleado(int legajo, Empresa empresa) {
        for (Empleado empleado : empresa.getEmpleados()) {
            if(legajo == empleado.getLegajo()){
                return true;
            }
        }
        return false;
    }

    private static void addNivelAcademico(int codigo, String nivel, double porcentajeAumento , ArrayList<NivelAcademico> nivelesAcademicos) {
        NivelAcademico nivelAcademico = new NivelAcademico();
        nivelAcademico.setCodigo(codigo);
        nivelAcademico.setNivel(nivel);
        nivelAcademico.setPorcentajeAumento(porcentajeAumento);
        nivelesAcademicos.add(nivelAcademico);
    }
}
