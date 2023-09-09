package test;

import java.util.Iterator;
import java.util.Vector;

import modelo.Cuenta;
import modelo.Movimiento;

import org.junit.Before;
import org.junit.Test;
//Para acceder a estos assert mas especificos escribir assert. y el IDE ofrecer� metodos mas complejos
import static org.junit.Assert.*;

public class CuentaTest 
{
	private Cuenta cuenta;
	private Vector movimientos;

	@Before // La etiqueta @Before indica que este método se ejecuta antes que el método @Test
	public void setUp() throws Exception  // Este método inicializa atributos para todos los métodos de prueba
	{
		cuenta = new Cuenta("20356298-33", "Garc�a Enrique");
		movimientos=null;
	// El estado de los atributos no se comparte, es decir si el estado de un atributo cambia después de ser usado por un método de prueba este vuelve al estado que tenía al principio para el siguiente método de prueba
	}

	
	@Test // La etiquta @Test indica que el método es un caso de prueba
	public void testIngresar() 
	{
		double saldoAnterior = cuenta.getSaldo();
		try {
			cuenta.ingresar(1000);
			assertTrue(cuenta.getSaldo() == saldoAnterior + 1000.0); // Las condiciones de prueba son evaluadas con métodos de comprobación del tipo “asserts”		
		}
		catch (Exception e) 
		{
			fail("No deber�a haber fallado");
		}
	}
	
	
	@Test
	public void testIngresarMontoNegativo() 
	{
		double saldoAnterior = cuenta.getSaldo();
		try {
			cuenta.ingresar(-1000);
		}
		catch (Exception e) 
		{
			assertTrue("Fallo - Permitio ingresar importe negativo", cuenta.getSaldo()==saldoAnterior);
		}
	}
	
	
	@Test
	public void testNoPermiteRetirarConFondosInsuficientes() 
	{
		double saldoAnterior = cuenta.getSaldo();
		try 
		{
			cuenta.retirar(1000);
		}
		catch (Exception e) 
		{
			assertTrue("Permitio retirar habiendo fondos insuficientes",saldoAnterior==cuenta.getSaldo());
		}
		
	}
	
	
	@Test
	public void testIngresosGeneranMovimientos() 
	{
		Movimiento mov1=null;
		Movimiento mov2=null;
		Movimiento mov3=null;
		
		try 
		{
			cuenta.ingresar(500.00);
			cuenta.ingresar(1000.00);
			cuenta.retirar(1000);
			
			movimientos = cuenta.getMovimientos();
			
			mov1= (Movimiento) movimientos.get(0);
			mov2= (Movimiento) movimientos.get(1);
			mov3= (Movimiento) movimientos.get(2);
			
			assertTrue(mov1.getImporte() == 500);
			assertTrue(mov2.getImporte() == 1000);
			assertTrue(mov3.getImporte() == -1000);
		}
		catch (Exception e) 
		{
			fail("Error al ingresar importe");
		}
		
	}
	
	
	//TESTS HECHOS POR MI
	@Test
	public void testRetirarMontoNegativo() 
	{
		double saldoAnterior = cuenta.getSaldo();
		try {
			cuenta.retirar(-1000);
			// Con assertTrue() los parámetros son un string y un booleano
			// El booleano representa la condición para aprobar la prueba
			// El string se muestra en caso de que el booleano sea falso, y por lo tanto, se falla la prueba
			assertTrue("Fallo - Permitió retirar un monto negativo, es decir ingreso un monto", cuenta.getSaldo() == saldoAnterior);
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
}