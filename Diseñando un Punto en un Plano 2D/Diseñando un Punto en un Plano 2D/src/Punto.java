public class Punto {

    // Atributos privados
    private double x;
    private double y;

    // Constructor que crea un punto en el origen (0,0)
    public Punto() {
        this.x = 0;
        this.y = 0;
    }

    // Constructor que recibe las coordenadas x e y
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Métodos de acceso
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Métodos para modificar las coordenadas
    public void setX(double nuevoX) {
        this.x = nuevoX;
    }

    public void setY(double nuevoY) {
        this.y = nuevoY;
    }

    // Desplaza el punto sumando deltaX y deltaY
    public void mover(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    // Distancia al origen (0,0): raíz de (x^2 + y^2)
    public double calcularDistanciaDesdeOrigen() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    // Imprime las coordenadas con formato (x, y)
    public void mostrarCoordenadas() {
        System.out.println("(" + x + ", " + y + ")");
    }
}