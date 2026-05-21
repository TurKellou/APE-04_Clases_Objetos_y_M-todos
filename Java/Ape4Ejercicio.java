import java.util.Scanner;

// ==========================================
// CLASE: Estudiante
// ==========================================
class Estudiante {
    // Atributos encapsulados del estudiante
    private String cedula;
    private String nombre;
    private String apellido;
    private double nota1;
    private double nota2;
    private double nota3;
    private double promedio;
    private String estado;

    // Constructor por defecto necesario para inicializar el arreglo en memoria
    public Estudiante() {
        this.cedula = "";
        this.nombre = "";
        this.apellido = "";
        this.nota1 = 0.0;
        this.nota2 = 0.0;
        this.nota3 = 0.0;
        this.promedio = 0.0;
        this.estado = "Reprobado";
    }

    // Constructor parametrizado para asignar valores directamente
    public Estudiante(String cedula, String nombre, String apellido, double nota1, double nota2, double nota3) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        calcularPromedio();
        determinarEstado();
    }

    // Métodos Getters y Setters para acceso seguro a los atributos
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public double getNota1() { return nota1; }
    public void setNota1(double nota1) { this.nota1 = nota1; calcularPromedio(); determinarEstado(); }

    public double getNota2() { return nota2; }
    public void setNota2(double nota2) { this.nota2 = nota2; calcularPromedio(); determinarEstado(); }

    public double getNota3() { return nota3; }
    public void setNota3(double nota3) { this.nota3 = nota3; calcularPromedio(); determinarEstado(); }

    public double getPromedio() { return promedio; }
    public String getEstado() { return estado; }

    // REQUERIMIENTO 3: Calcular automáticamente el promedio
    public void calcularPromedio() {
        this.promedio = (nota1 + nota2 + nota3) / 3.0;
    }

    // ESPECIFICACIÓN: El estudiante aprueba si el promedio es >= 7.00
    public void determinarEstado() {
        if (this.promedio >= 7.00) {
            this.estado = "Aprobado";
        } else {
            this.estado = "Reprobado";
        }
    }

    // Imprime de forma ordenada los datos individuales del estudiante
    public void mostrarInformacion() {
        // Se usa String.format para limitar los decimales del promedio a dos en la salida de consola
        System.out.println("Cedula: " + cedula + " | Alumno: " + apellido + " " + nombre 
             + " | Notas: [" + nota1 + ", " + nota2 + ", " + nota3 + "]"
             + " | Promedio: " + String.format("%.2f", promedio) + " | Estado: " + estado);
    }
}

// ==========================================
// CLASE PRINCIPAL: Main
// ==========================================
public class Ape4Ejercicio {

    // REQUERIMIENTO 7: Validar que las notas estén estrictamente entre 0 y 10
    public static double solicitarNotaValidada(Scanner teclado, String numeroNota) {
        double nota;
        while (true) {
            System.out.print("  Ingrese la " + numeroNota + " (0 - 10): ");
            if (teclado.hasNextDouble()) {
                nota = teclado.nextDouble();
                if (nota >= 0.0 && nota <= 10.0) {
                    return nota; // Retorna la nota solo si es válida
                } else {
                    System.out.println("  [Error] Nota invalida. Debe ser un numero entre 0 y 10. Intente de nuevo.");
                }
            } else {
                System.out.println("  [Error] Entrada no valida. Debe ingresar un numero. Intente de nuevo.");
                teclado.next(); // Limpia la entrada incorrecta (letras, símbolos) para evitar bucles infinitos
            }
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // REQUERIMIENTO 1: Registrar mínimo 5 estudiantes
        final int TOTAL_ESTUDIANTES = 5;
        Estudiante[] listaEstudiantes = new Estudiante[TOTAL_ESTUDIANTES];

        int aprobados = 0;
        int reprobados = 0;

        System.out.println("=== SISTEMA DE REGISTRO DE ESTUDIANTES (JAVA) ===\n");

        // Bucle para el ingreso de datos de cada uno de los estudiantes
        for (int i = 0; i < TOTAL_ESTUDIANTES; i++) {
            System.out.println("Estudiante #" + (i + 1) + ":");
            System.out.print("  Cedula: ");
            String cedula = teclado.next();
            System.out.print("  Nombre: ");
            String nombre = teclado.next();
            System.out.print("  Apellido: ");
            String apellido = teclado.next();

            // REQUERIMIENTO 2: Ingresar las 3 notas usando el método de validación
            double n1 = solicitarNotaValidada(teclado, "nota 1");
            double n2 = solicitarNotaValidada(teclado, "nota 2");
            double n3 = solicitarNotaValidada(teclado, "nota 3");
            System.out.println("-------------------------------------------");

            // Instanciación del objeto y almacenamiento en el arreglo de Java
            listaEstudiantes[i] = new Estudiante(cedula, nombre, apellido, n1, n2, n3);
        }

        // REQUERIMIENTO 4: Mostrar el listado completo de estudiantes registrados
        System.out.println("\n=== LISTADO COMPLETO DE ESTUDIANTES ===");
        for (int i = 0; i < TOTAL_ESTUDIANTES; i++) {
            listaEstudiantes[i].mostrarInformacion();

            // Conteo de estudiantes según la condición del estado asignado
            if (listaEstudiantes[i].getEstado().equals("Aprobado")) {
                aprobados++;
            } else {
                reprobados++;
            }
        }
        System.out.println("=======================================");

        // REQUERIMIENTO 5 y 6: Mostrar métricas de aprobación
        System.out.println("\n=== ESTADISTICAS DEL GRUPO ===");
        System.out.println("Estudiantes Aprobados (Promedio >= 7.00): " + aprobados);
        System.out.println("Estudiantes Reprobados (Promedio < 7.00): " + reprobados);
        System.out.println("==============================");
        
        teclado.close(); // Se cierra el Scanner al terminar el programa
    }
}