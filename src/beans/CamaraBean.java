package beans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.CaptureEvent;

import constants.Constantes;
 
@Named
@ViewScoped
public class CamaraBean implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filename;
	
	@Inject
	private MedicoResidenteBean medicoresidentebean;
	
	private boolean mostrar = true;
     
    private String getRandomImageName() {
        long nome = Calendar.getInstance().getTimeInMillis();
         
        return String.valueOf(nome);
    }
 
    public String getFilename() {
    	if(filename == null || filename.equalsIgnoreCase("")){
    		this.filename = "fotoDefault";
    	}
    	
        return filename;
    }
    
    public String getFilenameCompleto() {
    	return Constantes.wilflyUrl+"img/"+getFilename()+".jpeg";
    }
     
    public void oncapture(CaptureEvent captureEvent) {
    	filename = getRandomImageName();
        String dir = Constantes.fotosUrl;

        try {
        byte[] data = captureEvent.getData();
        
        File pasta = new File(dir);
        
        if(!pasta.exists()){
        	System.out.println("Criando pasta: "+dir);
        	pasta.mkdir();
        }
         
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator 
        + "imagens" + File.separator + "cam" + File.separator + filename + ".jpeg";
        String newFileName2 = Constantes.fotosUrl+"fotos" + File.separator + filename + ".jpeg";
        
        System.err.println("getRealPath: "+newFileName);
         
        FileImageOutputStream imageOutput;
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            imageOutput = new FileImageOutputStream(new File(newFileName2));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
        
        medicoresidentebean.getMedico().setPathFoto(filename);
    }
    
    public void oncapture2(CaptureEvent captureEvent) {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();
         
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator 
        + "imagens" + File.separator + "cam" + File.separator + filename + ".jpeg";
         
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
        
        medicoresidentebean.getMedico().setPathFoto(filename);
        this.mostrar = false;
    }

	public MedicoResidenteBean getMedicoresidentebean() {
		return medicoresidentebean;
	}

	public void setMedicoresidentebean(MedicoResidenteBean medicoresidentebean) {
		this.medicoresidentebean = medicoresidentebean;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
}
