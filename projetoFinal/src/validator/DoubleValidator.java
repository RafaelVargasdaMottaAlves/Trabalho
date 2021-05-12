package validator;

public class DoubleValidator {
	
	public DoubleValidator() {
		
	}
	
	public boolean isDouble(String str) {
	    try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
