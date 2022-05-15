/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import baseDatos.ProductoDaoImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.Producto;
import modelo.Venta;
import modelo.excepciones.AplicacionExcepcion;
import modelo.excepciones.ExcepcionProducto;
import modelo.ventas.FabricaPago;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class VentaDeProductosController implements Initializable {
	// lista de productosOfertados
	@FXML
	private TableView<Producto> tabListaProductos;
	@FXML
	private TableColumn<Producto, String> columProductos;
	@FXML
	private TableColumn<Producto, Double> columCostoVenta;
	// lista de compra
	@FXML
	private TableView<Producto> tabListaCompra;
	@FXML
	private TableColumn<Producto, String> columProductoCompra;
	@FXML
	private TableColumn<Producto, Double> columCostoCompra;
    @FXML
    private TableColumn<Producto, Double> cantidad;
    @FXML
    private Button btnPagoEfectivo;
    @FXML
    private Button btnPagoTarjeta;
    @FXML
    private Button btnBuscarProducto;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextField txtCosto;
    // lista de productos registrados en la base de datos
    ObservableList<Producto> listaProductosVenta;
   
    @FXML
    private TextField txtProductoVenta;
    @FXML
    private TextField txtCantidadProductosVenta;
    @FXML
    private Button btnAgregarProductoCompra;
    @FXML
    private Button btnCancelarSeleccion;
    @FXML
    private TextField txtTotalCompra;
    @FXML
    private TextArea areaInfoProducto;
   
   private  Venta ventaProductos= new Venta();
   private  ProductoDaoImp productoDao = new ProductoDaoImp();
  
    private TextField txtPagoEfectivo;
    @FXML
    private Button btnRealizarVenta;
   
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaProductos();
    }    

    @FXML
    private void cancelarVenta(ActionEvent event) {
    }

    @FXML
    private void cancelarProducto(ActionEvent event) {
    }

    @FXML
    private void salirVentas(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    private void informarVentas(ActionEvent event) {
    	String ayuda="En venta de productos:" + "\n"
                + "1- Selecciona algun producto de la lista de los productos. " + "\n"
                + "2- ingresa la cantidad de productos\n"
                + "3- Agrega el producto a la lista de compra. \n" 
                + "4- Realiza el pago en tarjeta o efectivo \n "
                + "o busca algun producto y realiza el proceso desde el punto 2";
                
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
	    alerta.setTitle("Ventas");
	    alerta.setContentText(ayuda);
	    alerta.showAndWait();
    }

    @FXML
    private void pagarEnEfectivo(ActionEvent event) throws AplicacionExcepcion {
   
     
    }

    @FXML
    private void PagoConTarjeta(ActionEvent event) throws AplicacionExcepcion {
    
    	
    }

    @FXML
	private void buscarProductoVenta(ActionEvent event) {
		ProductoDaoImp productoDao = new ProductoDaoImp();
		if (txtNombreProducto.getText().length() != 0) {

			Producto producto = productoDao.buscarProducto(txtNombreProducto.getText());
			if (producto != null) {
				txtProductoVenta.setText(producto.getNombreProducto());
				txtCosto.setText(String.valueOf(producto.getCostoUnidad()));
				txtCantidadProductosVenta.setText("1");
				areaInfoProducto.setText(producto.getDescripcion());
				btnAgregarProductoCompra.setDisable(false);
				btnCancelarSeleccion.setDisable(false);
			} else {
				cancelarSeleccion(event);

			}
		} else {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Busqueda Producto");
			alerta.setContentText("Escribe el nombre del producto que deseas buscar");
			alerta.showAndWait();
		}
	}
    
    public void inicializarTablaProductos() {

		try {
			// observableList con un la lista de productos que devuelve productoDao
			listaProductosVenta = FXCollections.observableArrayList(productoDao.consultarProductos());
			tabListaProductos.setItems(listaProductosVenta);
			columProductos.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
			columCostoVenta.setCellValueFactory(new PropertyValueFactory<Producto, Double>("costoUnidad"));

		} catch (ExcepcionProducto e) {
			e.printStackTrace();
		}

	}

    @FXML
    private void seleccionarProductos(MouseEvent event) {
    	
    	txtProductoVenta.setText(tabListaProductos.getSelectionModel().getSelectedItem().getNombreProducto());
    	txtCosto.setText(String.valueOf(tabListaProductos.getSelectionModel().getSelectedItem().getCostoUnidad()));
    	txtCantidadProductosVenta.setText("1");
    	areaInfoProducto.setText(tabListaProductos.getSelectionModel().getSelectedItem().getDescripcion());
        btnAgregarProductoCompra.setDisable(false);
        btnCancelarSeleccion.setDisable(false);
    }

    @FXML
    private void AgregarProductoCompra(ActionEvent event) throws AplicacionExcepcion {
		Producto productoVenta = productoDao.buscarProducto(txtProductoVenta.getText());
		ventaProductos.setCantidadProducto(Integer.parseInt(txtCantidadProductosVenta.getText()));
		ventaProductos.agregarProductos(productoVenta,Integer.parseInt(txtCantidadProductosVenta.getText()));
		txtTotalCompra.setText(String.valueOf(ventaProductos.calcularTotalVenta()));
		cancelarSeleccion(event);
		btnPagoEfectivo.setDisable(false);
		btnPagoTarjeta.setDisable(false);
    }

    @FXML
    private void cancelarSeleccion(ActionEvent event) {
    	
		txtCosto.setText("");
		areaInfoProducto.setText("");
		txtNombreProducto.setText("");
		txtProductoVenta.setText("");
		txtCantidadProductosVenta.setText("");
		btnAgregarProductoCompra.setDisable(true);
		btnCancelarSeleccion.setDisable(true);
    	
    }

    @FXML
    private void PagarVenta(ActionEvent event) throws AplicacionExcepcion {
    	
		if (!txtPagoEfectivo.getText().isEmpty()) {
			double pago = Double.parseDouble(txtPagoEfectivo.getText());
			FabricaPago pagoEfectivo = new FabricaPago();
			pagoEfectivo.obtenerTipoPago("efectivo", ventaProductos.getTotalVenta(), pago);
		}
    
}

}
