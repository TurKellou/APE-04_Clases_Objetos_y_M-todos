#include <iostream>
#include <string>

using namespace std;

// ==========================================
// CLASE: Estudiante
// ==========================================
class Estudiante {
private:
    // Atributos encapsulados del estudiante
    string cedula;
    string nombre;
    string apellido;
    double nota1;
    double nota2;
    double nota3;
    double promedio;
    string estado;

public:
    // Constructor por defecto necesario para inicializar el arreglo en memoria
    Estudiante() {
        cedula = ""; nombre = ""; apellido = "";
        nota1 = 0.0; nota2 = 0.0; nota3 = 0.0;
        promedio = 0.0; estado = "Reprobado";
    }

    // Constructor parametrizado para asignar valores directamente
    Estudiante(string c, string n, string a, double n1, double n2, double n3) {
        cedula = c;
        nombre = n;
        apellido = a;
        nota1 = n1;
        nota2 = n2;
        nota3 = n3;
        calcularPromedio();
        determinarEstado();
    }

    // Métodos Getters y Setters para acceso seguro a los atributos
    string getCedula() { return cedula; }
    void setCedula(string c) { cedula = c; }

    string getNombre() { return nombre; }
    void setNombre(string n) { nombre = n; }

    string getApellido() { return apellido; }
    void setApellido(string a) { apellido = a; }

    double getNota1() { return nota1; }
    void setNota1(double n1) { nota1 = n1; calcularPromedio(); determinarEstado(); }

    double getNota2() { return nota2; }
    void setNota2(double n2) { nota2 = n2; calcularPromedio(); determinarEstado(); }

    double getNota3() { return nota3; }
    void setNota3(double n3) { nota3 = n3; calcularPromedio(); determinarEstado(); }

    double getPromedio() { return promedio; }
    string getEstado() { return estado; }

    // REQUERIMIENTO 3: Calcular automáticamente el promedio
    void calcularPromedio() {
        promedio = (nota1 + nota2 + nota3) / 3.0;
    }

    // NUEVA ESPECIFICACIÓN: El estudiante aprueba ÚNICAMENTE si el promedio es >= 7.00
    void determinarEstado() {
        if (promedio >= 7.00) {
            estado = "Aprobado";
        } else {
            estado = "Reprobado";
        }
    }

    // Imprime de forma ordenada los datos individuales del estudiante
    void mostrarInformacion() {
        cout << "Cedula: " << cedula << " | Alumno: " << apellido << " " << nombre 
             << " | Notas: [" << nota1 << ", " << nota2 << ", " << nota3 << "]"
             << " | Promedio: " << promedio << " | Estado: " << estado << "\n";
    }
};

// ==========================================
// FUNCIONES AUXILIARES DE VALIDACIÓN
// ==========================================

// REQUERIMIENTO 7: Validar que las notas estén estrictamente entre 0 y 10
double solicitarNotaValidada(string numeroNota) {
    double nota;
    while (true) {
        cout << "  Ingrese la " << numeroNota << " (0 - 10): ";
        if (cin >> nota && nota >= 0.0 && nota <= 10.0) {
            return nota; // Retorna la nota solo si es válida
        } else {
            cout << "  [Error] Nota invalida. Debe ser un numero entre 0 y 10. Intente de nuevo.\n";
            cin.clear(); // Limpia el flag de error de cin (evita bucles infinitos por letras)
            cin.ignore(10000, '\n'); // Descarta el buffer incorrecto
        }
    }
}

// ==========================================
// FUNCIÓN PRINCIPAL
// ==========================================
int main() {
    // REQUERIMIENTO 1: Registrar mínimo 5 estudiantes
    const int TOTAL_ESTUDIANTES = 5;
    Estudiante listaEstudiantes[TOTAL_ESTUDIANTES];

    int aprobados = 0;
    int reprobados = 0;

    cout << "=== SISTEMA DE REGISTRO DE ESTUDIANTES ===\n\n";

    // Bucle para el ingreso de datos de cada uno de los estudiantes
    for (int i = 0; i < TOTAL_ESTUDIANTES; i++) {
        string cedula, nombre, apellido;
        double n1, n2, n3;

        cout << "Estudiante #" << (i + 1) << ":\n";
        cout << "  Cedula: ";
        cin >> cedula;
        cout << "  Nombre: ";
        cin >> nombre;
        cout << "  Apellido: ";
        cin >> apellido;

        // REQUERIMIENTO 2: Ingresar las 3 notas usando la función de validación
        n1 = solicitarNotaValidada("nota 1");
        n2 = solicitarNotaValidada("nota 2");
        n3 = solicitarNotaValidada("nota 3");
        cout << "-------------------------------------------\n";

        // Asignación de datos al objeto correspondiente del arreglo
        listaEstudiantes[i] = Estudiante(cedula, nombre, apellido, n1, n2, n3);
    }

    // REQUERIMIENTO 4: Mostrar el listado completo de estudiantes registrados
    cout << "\n=== LISTADO COMPLETO DE ESTUDIANTES ===\n";
    for (int i = 0; i < TOTAL_ESTUDIANTES; i++) {
        listaEstudiantes[i].mostrarInformacion();

        // Conteo de estudiantes según la condición del estado asignado (>= 7.00)
        if (listaEstudiantes[i].getEstado() == "Aprobado") {
            aprobados++;
        } else {
            reprobados++;
        }
    }
    cout << "=======================================\n";

    // REQUERIMIENTO 5 y 6: Mostrar métricas cuantitativas de aprobación
    cout << "\n=== ESTADISTICAS DEL GRUPO ===" << endl;
    cout << "Estudiantes Aprobados (Promedio >= 7.00): " << aprobados << endl;
    cout << "Estudiantes Reprobados (Promedio < 7.00): " << reprobados << endl;
    cout << "==============================" << endl;

    return 0;
}