package view;

public class MVCView
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar los datos.");
			System.out.println("2. Consultar una zona por id.");
			System.out.println("3. Consultar las zonas con un id en un rango específico.");


		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
}
