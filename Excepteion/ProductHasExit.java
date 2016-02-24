package shop.management.system.exception;

public class ProductHasExit extends Exception {
	
	public ProductHasExit() {

	}

	public ProductHasExit(String message) {
		super(message);
	}

	public ProductHasExit(Throwable throwable) {
		super(throwable);
	}
	
}
