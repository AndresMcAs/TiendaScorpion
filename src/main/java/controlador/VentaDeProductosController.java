/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import baseDatos.ProductoDao;
import baseDatos.ProductoDaoImp;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Producto;
import modelo.Ticket;
import modelo.IValidable;
import modelo.ValidableImp;
import modelo.Venta;
import modelo.excepciones.AplicacionExcepcion;
import modelo.excepciones.ExcepcionProducto;
import modelo.ventas.ContextoPago;
import modelo.ventas.PagoEnEfectivo;
import modelo.ventas.PagoTarjeta;

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
	private TableColumn<Producto, String> cantidad;
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
	ObservableList<Producto> listaProductosCompra;

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
	@FXML
	private TextField txtMonto;
	private Venta ventaProductos = new Venta();
	private ProductoDao productoDao = new ProductoDaoImp();
	private double totalVenta;
	private IValidable validaDatos = new ValidableImp();
	private double monto;
	private int numProducto;
	private Producto productoVenta;
	@FXML
	private TextField txtCambio;
	@FXML
	private Button btnCancelarVenta;
	@FXML
	private Button btnCancelarProducto;
	@FXML
	private TextField txfNumeroTarjeta;
	@FXML
	private PasswordField passPin;
	@FXML
	private AnchorPane btnVerificaTarjeta;
	ContextoPago pago = new ContextoPago();
    @FXML
    private Button btnImprimir;
    
    private String nombreCajero;
    @FXML
    private TextField txtfNombreCajero;
    public String getNombreCajero() {
		return nombreCajero;
	}
    public void setNombreCajero(String nombreCajero) {
		this.nombreCajero = nombreCajero;
	}
    
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		inicializarTablaProductos();

		Platform.runLater(() -> {
			txtfNombreCajero.setText(getNombreCajero());
		});
	    
	}

	@FXML
	private void cancelarVenta(ActionEvent event) {

		// eliminamos todo lo que se encuentre en la venta
		cancelarSeleccion(event);
		txtCambio.setText("");
		txtMonto.setText("");
		txtMonto.setEditable(false);
		listaProductosCompra.clear();
		txtTotalCompra.setText("");
		btnCancelarVenta.setDisable(true);
		ventaProductos.cancelarVenta();
		btnPagoEfectivo.setDisable(true);
		btnPagoTarjeta.setDisable(true);
		passPin.setText("");
		passPin.setEditable(false);
		txfNumeroTarjeta.setText("");
		txfNumeroTarjeta.setEditable(false);
		txtCantidadProductosVenta.setEditable(false);
		numProducto = 0;
		
	}

	@FXML
	private void cancelarProducto(ActionEvent event) {
	}

	@FXML
	private void salirVentas(ActionEvent event) {
		Stage  stage=(Stage)this.btnBuscarProducto.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void informarVentas(ActionEvent event) {
		String ayuda = "En venta de productos:" + "\n" + "1- Selecciona algun producto de la lista de los productos. "
				+ "\n" + "2- ingresa la cantidad de productos\n" + "3- Agrega el producto a la lista de compra. \n"
				+ "4- Realiza el pago en tarjeta o efectivo \n "
				+ "o busca algun producto y realiza el proceso desde el punto 2";

		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Ventas");
		alerta.setContentText(ayuda);
		alerta.showAndWait();
	}

	 
	
	@FXML
	private void pagarEnEfectivo(ActionEvent event) throws AplicacionExcepcion {
		
		
		try {
			
			if (!txtMonto.getText().isBlank()) {
				
				if(validaDatos.validarCostoProducto(txtMonto.getText())) {
					
					while (totalVenta == pago.getAcumulador()) {
						pago.ejecutarPago(totalVenta, monto);
						
						
						}
					}
				}
			
		} catch(AplicacionExcepcion a) {}
			
		try {
		
			if (!txtMonto.getText().isBlank()) {
				if (validaDatos.validarCostoProducto(txtMonto.getText())) {
					
					monto = Double.parseDouble(txtMonto.getText());
					PagoEnEfectivo pagoE = new PagoEnEfectivo();
					pago.setContextoPago(pagoE);
					if (pago.ejecutarPago(totalVenta, monto)==true) {
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Ventas");
						alerta.setContentText("Pago realizado con exito!");
						alerta.showAndWait();
						
						for (Producto producto : ventaProductos.getProductos()) {
							productoDao.modificarProductoExistencias(producto, numProducto);
		                      
						} 
						desactivarBotonesPago();
					  pago.setMonto(monto);
					  pago.setCambio(pagoE.getCambio());
		              txtCambio.setText(String.format("%.2f",pago.getCambio()));
		              monto=0;
					   btnImprimir.setDisable(false);
					} else {
						
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Ventas");
						alerta.setContentText("falta efectivo para realizar la compra");
						alerta.showAndWait();
						
					}

				} else {
					throw new AplicacionExcepcion("Error solo se admiten numeros en el campo monto");
				}
			} 
			else {
                
				throw new AplicacionExcepcion("Por favor ingresa un monto con el que vas a pagar");
			}

		} catch (AplicacionExcepcion e) {
			
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Ventas");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
			
		}
	}

	@FXML
	private void PagoConTarjeta(ActionEvent event) throws AplicacionExcepcion {
		
		pago.setContextoPago(new PagoTarjeta());
		try {
			if (txfNumeroTarjeta.isEditable() && txfNumeroTarjeta.getText().length() != 0
					  && passPin.getText().length() != 0) {
				PagoTarjeta pagoTarjeta = new PagoTarjeta();
	          if(validaDatos.validarNumeroUnidades(passPin.getText())) {
				if (pagoTarjeta.verificarTarjeta(txfNumeroTarjeta.getText(), Integer.parseInt(passPin.getText()))) {
					pago.setContextoPago(pagoTarjeta);
					pago.ejecutarPago(totalVenta, totalVenta);
					if (pago.ejecutarPago(totalVenta,totalVenta)==true) {
						pago.setCambio(0.0);
						pago.setMonto(0);
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Ventas");
						alerta.setContentText("Pago realizado con exito!");
						alerta.showAndWait();
						for (Producto producto : ventaProductos.getProductos()) {
							productoDao.modificarProductoExistencias(producto, numProducto);

						}
						desactivarBotonesPago();
					} else {
						
						Alert alerta = new Alert(Alert.AlertType.INFORMATION);
						alerta.setTitle("Ventas");
						alerta.setContentText("Falta Credito para relalizar la venta" );
						alerta.showAndWait();
						
					}
				} else {
					throw new AplicacionExcepcion("Error en los datos de la tarjeta ");
				}
	          } else {
	        	  throw new AplicacionExcepcion("Error solo se permite numero en el pin");
	          }
	          } else {
				throw new AplicacionExcepcion("Error ingresa el numero y pin de la tarjeta");
			}
		} catch (AplicacionExcepcion e) {
			txtCambio.setText("0.0");
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Ventas");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		
		}
		
	
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
				txtCantidadProductosVenta.setEditable(true);
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
		txtCantidadProductosVenta.setEditable(true);
	}

	@FXML
	private void AgregarProductoCompra(ActionEvent event) {

		try {
			
			productoVenta = productoDao.buscarProducto(txtProductoVenta.getText());
			if (validaDatos.validarNumeroUnidades(txtCantidadProductosVenta.getText())) {
				ventaProductos.setCantidadProducto(Integer.parseInt(txtCantidadProductosVenta.getText()));
				// se necesita para verificar la existencias
				numProducto = Integer.parseInt(txtCantidadProductosVenta.getText());

				ventaProductos.agregarProductos(productoVenta, numProducto);

				txtTotalCompra.setText(String.valueOf(ventaProductos.calcularTotalVenta()));
				cancelarSeleccion(event);
				habilitarBotones();
				totalVenta = Double.parseDouble(txtTotalCompra.getText());
				// inicializamos la lista de compra
				inicializarTablaCompra();
			} else {
				numProducto = 0;
				throw new AplicacionExcepcion("Error:Solo se admiten numeros enteros en el campo cantidad");

			}

		} catch (AplicacionExcepcion e) {
			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Venta Productos");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
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


	/**
	 * inicializa la lista de compra con los productos agregados
	 */
	public void inicializarTablaCompra() {

		listaProductosCompra = FXCollections.observableArrayList(ventaProductos.getProductos());
		tabListaCompra.setItems(listaProductosCompra);
		columProductoCompra.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombreProducto"));
		columCostoCompra.setCellValueFactory(new PropertyValueFactory<Producto, Double>("costoUnidad"));
		// cantidad.setCellValueFactory(new
		// PropertyValueFactory<Producto,String>("unidades"));

	}

    @FXML
    private void imprimirTicket(ActionEvent event) {
    	
      Ticket t = new Ticket(ventaProductos,getNombreCajero(),pago);
      t.imprimirTicket();
      
      cancelarVenta(event);
      btnImprimir.setDisable(true);Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Ticket");
		alerta.setContentText("Imprimiendo ticket...");
		alerta.showAndWait();
      
      
    }
    
    private void desactivarBotonesPago() {
    	btnCancelarVenta.setDisable(true);
     	btnPagoEfectivo.setDisable(true);
	    btnPagoTarjeta.setDisable(true);
	    btnAgregarProductoCompra.setDisable(true);
	    btnImprimir.setDefaultButton(true);
	    txtMonto.setEditable(false);
	    txfNumeroTarjeta.setEditable(false);
	    txfNumeroTarjeta.setEditable(false);
	 
    }
    
    private void habilitarBotones() {
    	// habilitamos los botones para proceceder con la venta
		btnCancelarVenta.setDisable(false); // podemos cancelar la venta total
		btnPagoEfectivo.setDisable(false);// para habilitar los txt de monto
		btnPagoTarjeta.setDisable(false);// para habilitar los txt pin y el numero de tarjeta
		txtMonto.setEditable(true);
		txfNumeroTarjeta.setEditable(true);
		passPin.setEditable(true);
		
		
	    
    }

}
