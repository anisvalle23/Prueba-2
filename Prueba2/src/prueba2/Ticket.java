package prueba2;

public class Ticket {

    private String nombrePasajero;
    private double totalPagado;

    public Ticket(String nombrePasajero, double totalPagado) {
        this.nombrePasajero = nombrePasajero;
        this.totalPagado = totalPagado;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void print() {
        System.out.println("Pasajero: " + nombrePasajero + ", Total Pagado: $." + totalPagado);
    }
}
