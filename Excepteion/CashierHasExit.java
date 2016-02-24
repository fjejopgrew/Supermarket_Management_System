package shop.management.system.exception;

public class CashierHasExit extends Exception {

	public CashierHasExit() {

	}

	public CashierHasExit(String message) {
		super(message);
	}

	public CashierHasExit(Throwable throwable) {
		super(throwable);

	}
}
