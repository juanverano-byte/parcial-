package diseño;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * Calculadora básica a pantalla completa: suma, resta, multiplicación,
 * división, raíz, logaritmo y exponencial.
 * Proyecto: Rodolfo
 *
 * Suma, resta, multiplicación y división usan los dos números.
 * Raíz, logaritmo (base 10) y exponencial (e^x) usan solo el PRIMER número.
 */
public class vista extends javax.swing.JFrame {

    private static final Color FONDO_VENTANA = new Color(214, 217, 223);
    private static final Color AZUL = new Color(153, 204, 255);
    private static final Color VERDE_MENTA = new Color(153, 255, 204);
    private static final Color VERDE = new Color(153, 255, 153);

    // Campos de texto
    private JTextField txtnumero1;
    private JTextField txtnumero2;
    private JTextField txtresultado;

    // Botones
    private JButton suma;
    private JButton resta;
    private JButton multiplicacion;
    private JButton division;
    private JButton limpiar;
    private JButton raiz;
    private JButton logaritmo;
    private JButton exponencial;

    public vista() {
        initComponents();
        this.setLocationRelativeTo(null); // centra la ventana en la pantalla
    }

    // ---------- utilidades de diseño ----------

    private GridBagConstraints c(int x, int y, int ancho, double wx, double wy,
            int fill, int anchor, int ipadx, int ipady) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x;
        g.gridy = y;
        g.gridwidth = ancho;
        g.weightx = wx;
        g.weighty = wy;
        g.fill = fill;
        g.anchor = anchor;
        g.ipadx = ipadx;
        g.ipady = ipady;
        g.insets = new Insets(10, 15, 10, 15);
        return g;
    }

    private JPanel crearPanel(Color fondo, String titulo) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(fondo);
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(fondo.darker(), 2, true),
                titulo,
                TitledBorder.LEFT,
                TitledBorder.ABOVE_TOP,
                new Font("Arial", Font.BOLD | Font.ITALIC, 18),
                Color.BLACK));
        return p;
    }

    private JLabel crearLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(new Font("Arial", Font.PLAIN, 18));
        return l;
    }

    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Arial", Font.PLAIN, 18));
        b.setFocusPainted(false);
        return b;
    }

    private JTextField crearCampo() {
        JTextField t = new JTextField();
        t.setFont(new Font("Arial", Font.PLAIN, 18));
        t.setPreferredSize(new Dimension(200, 36));
        return t;
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora");

        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(FONDO_VENTANA);
        contenedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contenedor);

        // Columnas laterales vacías para que el contenido quede centrado
        JPanel izq = new JPanel();
        izq.setOpaque(false);
        JPanel der = new JPanel();
        der.setOpaque(false);
        GridBagConstraints gi = c(0, 0, 1, 0.15, 0, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0);
        GridBagConstraints gd = c(2, 0, 1, 0.15, 0, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0);
        contenedor.add(izq, gi);
        contenedor.add(der, gd);

        // Título
        JLabel titulo = new JLabel("CALCULADORA BASICA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 34));
        contenedor.add(titulo, c(1, 0, 1, 0.7, 0.08, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 0, 0));

        // Panel 1: datos
        JPanel panelDatos = crearPanel(AZUL, "Ingresa tus datos:");
        txtnumero1 = crearCampo();
        txtnumero2 = crearCampo();
        panelDatos.add(crearLabel("Ingresar Primer Numero:"),
                c(0, 0, 1, 0.5, 1, GridBagConstraints.NONE, GridBagConstraints.WEST, 0, 0));
        panelDatos.add(txtnumero1,
                c(1, 0, 1, 0.5, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 0, 0));
        panelDatos.add(crearLabel("Ingresar Segundo Numero:"),
                c(0, 1, 1, 0.5, 1, GridBagConstraints.NONE, GridBagConstraints.WEST, 0, 0));
        panelDatos.add(txtnumero2,
                c(1, 1, 1, 0.5, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 0, 0));
        contenedor.add(panelDatos, c(1, 1, 1, 0.7, 0.25, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0));

        // Panel 2: operaciones
        JPanel panelOperaciones = crearPanel(VERDE_MENTA, "Elige la Operacion Deseada:");
        suma = crearBoton("Suma");
        multiplicacion = crearBoton("Multiplicacion");
        limpiar = crearBoton("Limpiar");
        resta = crearBoton("Resta");
        division = crearBoton("Division");
        raiz = crearBoton("Raiz");
        logaritmo = crearBoton("Logaritmo");
        exponencial = crearBoton("Exponencial");

        int cen = GridBagConstraints.CENTER;
        int non = GridBagConstraints.NONE;
        panelOperaciones.add(suma, c(0, 0, 1, 1, 1, non, GridBagConstraints.WEST, 30, 12));
        panelOperaciones.add(multiplicacion, c(2, 0, 1, 1, 1, non, GridBagConstraints.EAST, 30, 12));
        panelOperaciones.add(limpiar, c(1, 1, 1, 1, 1, non, cen, 30, 12));
        panelOperaciones.add(resta, c(0, 2, 1, 1, 1, non, GridBagConstraints.WEST, 30, 12));
        panelOperaciones.add(division, c(2, 2, 1, 1, 1, non, GridBagConstraints.EAST, 30, 12));
        panelOperaciones.add(raiz, c(0, 3, 1, 1, 1, non, GridBagConstraints.WEST, 30, 12));
        panelOperaciones.add(logaritmo, c(1, 3, 1, 1, 1, non, cen, 30, 12));
        panelOperaciones.add(exponencial, c(2, 3, 1, 1, 1, non, GridBagConstraints.EAST, 30, 12));
        contenedor.add(panelOperaciones, c(1, 2, 1, 0.7, 0.45, GridBagConstraints.BOTH, cen, 0, 0));

        // Panel 3: resultados
        JPanel panelResultados = crearPanel(VERDE, "Tus Resultados:");
        txtresultado = new JTextField();
        txtresultado.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 28));
        txtresultado.setForeground(new Color(0, 0, 204));
        txtresultado.setHorizontalAlignment(SwingConstants.CENTER);
        txtresultado.setEditable(false);
        txtresultado.setBackground(Color.WHITE);
        txtresultado.setPreferredSize(new Dimension(320, 56));
        panelResultados.add(crearLabel("Resultado:"),
                c(0, 0, 1, 0, 1, non, GridBagConstraints.EAST, 0, 0));
        panelResultados.add(txtresultado,
                c(1, 0, 1, 0, 1, non, GridBagConstraints.WEST, 0, 0));
        contenedor.add(panelResultados, c(1, 3, 1, 0.7, 0.25, GridBagConstraints.BOTH, cen, 0, 0));

        // Eventos
        suma.addActionListener(this::sumaActionPerformed);
        resta.addActionListener(this::restaActionPerformed);
        multiplicacion.addActionListener(this::multiplicacionActionPerformed);
        division.addActionListener(this::divisionActionPerformed);
        limpiar.addActionListener(this::limpiarActionPerformed);
        raiz.addActionListener(this::raizActionPerformed);
        logaritmo.addActionListener(this::logaritmoActionPerformed);
        exponencial.addActionListener(this::exponencialActionPerformed);

        pack();
        setMinimumSize(new Dimension(700, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH); // pantalla completa
    }

    // ---------- lectura y validación ----------

    private void mostrarError(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
        txtresultado.setText(null);
    }

    /** Convierte un texto en número; devuelve null si no es válido. */
    private Double convertir(String texto) {
        try {
            return Double.parseDouble(texto.trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /** Lee los dos números; devuelve null (y avisa) si alguno no es válido. */
    private double[] leerNumeros() {
        Double n1 = convertir(txtnumero1.getText());
        Double n2 = convertir(txtnumero2.getText());
        if (n1 == null || n2 == null) {
            mostrarError("Ingresa dos números válidos.", "Dato incorrecto", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return new double[]{n1, n2};
    }

    /** Lee solo el primer número; devuelve null (y avisa) si no es válido. */
    private Double leerPrimerNumero() {
        Double n1 = convertir(txtnumero1.getText());
        if (n1 == null) {
            mostrarError("Ingresa un número válido en el primer campo.", "Dato incorrecto", JOptionPane.WARNING_MESSAGE);
        }
        return n1;
    }

    // ---------- botones ----------

    private void sumaActionPerformed(java.awt.event.ActionEvent evt) {
        double[] n = leerNumeros();
        if (n == null) return;
        double r = n[0] + n[1];
        txtresultado.setText(String.valueOf(r));
    }

    private void restaActionPerformed(java.awt.event.ActionEvent evt) {
        double[] n = leerNumeros();
        if (n == null) return;
        double r = n[0] - n[1];
        txtresultado.setText(String.valueOf(r));
    }

    private void multiplicacionActionPerformed(java.awt.event.ActionEvent evt) {
        double[] n = leerNumeros();
        if (n == null) return;
        double r = n[0] * n[1];
        txtresultado.setText(String.valueOf(r));
    }

    private void divisionActionPerformed(java.awt.event.ActionEvent evt) {
        double[] n = leerNumeros();
        if (n == null) return;
        if (n[1] == 0) {
            mostrarError("No se puede dividir entre cero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double r = n[0] / n[1];
        txtresultado.setText(String.valueOf(r));
    }

    private void raizActionPerformed(java.awt.event.ActionEvent evt) {
        Double n1 = leerPrimerNumero();
        if (n1 == null) return;
        if (n1 < 0) {
            mostrarError("No existe la raíz de un número negativo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double r = Math.sqrt(n1);
        txtresultado.setText(String.valueOf(r));
    }

    private void logaritmoActionPerformed(java.awt.event.ActionEvent evt) {
        Double n1 = leerPrimerNumero();
        if (n1 == null) return;
        if (n1 <= 0) {
            mostrarError("El logaritmo solo existe para números mayores que cero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double r = Math.log10(n1); // base 10 (para el natural usa Math.log)
        txtresultado.setText(String.valueOf(r));
    }

    private void exponencialActionPerformed(java.awt.event.ActionEvent evt) {
        Double n1 = leerPrimerNumero();
        if (n1 == null) return;
        double r = Math.exp(n1); // e elevado al primer número
        if (Double.isInfinite(r)) {
            mostrarError("El resultado es demasiado grande.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        txtresultado.setText(String.valueOf(r));
    }

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {
        txtresultado.setText(null);
        txtnumero1.setText(null);
        txtnumero2.setText(null);
        txtnumero1.requestFocus();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new vista().setVisible(true));
    }
}
