package shop.management.system.exception;

/**
 * 收银员用户名不存在异常
 * @author Hang Gao
 *
 */
public class CashierNotFound extends Exception {

	public CashierNotFound() {

	}

	public CashierNotFound(String message) {
		super(message);

	}

	public CashierNotFound(Throwable throwable) {
		super(throwable);

	}
	
	
	
}
