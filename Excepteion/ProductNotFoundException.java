package shop.management.system.exception;

public class ProductNotFoundException extends Exception {

	public ProductNotFoundException() {
	
	}

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(Throwable throwable) {
		super(throwable);
	}
	
	
}
