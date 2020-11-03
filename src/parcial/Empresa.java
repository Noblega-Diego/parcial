package parcial;

import java.util.ArrayList;

public class Empresa {
    private String cuit;
    private String razonSocial;
    private ArrayList<Empleado> empleados;

    public Empresa() {
        this.empleados = new ArrayList<Empleado>();
    }
    public void addEmpleado(Empleado empleado) {
        if(empleados == null){
            empleados = new ArrayList<Empleado>();
        }
        this.empleados.add(empleado);
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    
}
