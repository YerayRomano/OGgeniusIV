package auxiliar;

import java.io.File;

public class Snippets {
	public static String fotoUsuario(int cod_usr) {
		String rutaImagen = "perfiles/default.jpg";
		String directorio = "C:/Users/alumno_t/eclipse-workspace/OGgenius/WebContent/perfiles/";
		File tieneImagen = new File(directorio + cod_usr + ".jpg");
		if (tieneImagen.exists()) {
			rutaImagen = "perfiles/" + cod_usr + ".jpg";
		}
		return rutaImagen;
	}
}
