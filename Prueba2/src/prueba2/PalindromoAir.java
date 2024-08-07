
package prueba2;


public class PalindromoAir {


    Ticket[] asiento;

    public PalindromoAir() {
        asiento = new Ticket[30];
    }

    public int firstAvailable() {
        return firstAvailable(0);
    }

    private int firstAvailable(int index) {
        if (index >= asiento.length) {
            return -1;
        }
        if (asiento[index] == null) {
            return index;
        }
        return firstAvailable(index + 1);
    }

    public int searchPassenger(String name) {
        return searchPassenger(name, 0);
    }

    private int searchPassenger(String name, int index) {
        if (index >= asiento.length) {
            return -1;
        }
        if (asiento[index] != null && asiento[index].getNombrePasajero().equals(name)) {
            return index;
        }
        return searchPassenger(name, index + 1);
    }

    public boolean isPalindromo(String name) {
        return isPalindromo(name, 0, name.length() - 1);
    }

    private boolean isPalindromo(String name, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (name.charAt(start) != name.charAt(end)) {
            return false;
        }
        return isPalindromo(name, start + 1, end - 1);
    }

    public void printPassengers() {
        printPassengers(0);
    }

    private void printPassengers(int index) {
        if (index >= asiento.length) {
            return;
        }
        if (asiento[index] != null) {
            asiento[index].print();
        }
        printPassengers(index + 1);
    }

    public double income() {
        return income(0);
    }

    private double income(int index) {
        if (index >= asiento.length) {
            return 0;
        }
        if (asiento[index] != null) {
            return asiento[index].getTotalPagado() + income(index + 1);
        }
        return income(index + 1);
    }

    public void reset() {
        reset(0);
    }

    private void reset(int index) {
        if (index >= asiento.length) {
            return;
        }
        asiento[index] = null;
        reset(index + 1);
    }

    public void sellTicket(String name) {
        int posicion = firstAvailable();
        if (posicion == -1) {
            System.out.println("No hay asientos disponibles.");
            return;
        }

        double precio = 800;
        if (isPalindromo(name)) {
            precio *= 0.8;
        }

        asiento[posicion] = new Ticket(name, precio);
        System.out.println("Ticket vendido, nombre pasajero: " + name + ", precio $." + precio);
    }

    public boolean cancelTicket(String name) {
        int posicion = searchPassenger(name);
        if (posicion == -1) {
            return false;
        }

        asiento[posicion] = null;
        return true;
    }

    public void dispatch() {
        System.out.println("Ingreso total generado: $." + income());
        reset();
        System.out.println("Los asientos del avion han sido reseteados.");
    }
}