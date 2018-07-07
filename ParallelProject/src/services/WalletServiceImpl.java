package services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import beans.Customer;
import beans.Wallet;
import exception.InvalidInputException;
import repo.WalletRepo;
import repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {

	static Scanner sc = new Scanner(System.in);
	private WalletRepo repo;

	public WalletServiceImpl(Map<String, Customer> data) {
		repo = new WalletRepoImpl(data);
	}

	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}

	public WalletServiceImpl() {
		repo = new WalletRepoImpl(new HashMap<String, Customer>());
	}

	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) {
		while (true) {
			if (ValidateMobile(mobileno)) {
				break;
			} else {
				System.err.println("wrong mobile number it should be 10 digits. eg:1234567890");
				System.err.println("enter again");
				mobileno = sc.next();
			}
		}
		Customer customer = new Customer(name, mobileno, new Wallet(amount));
		boolean result = repo.save(customer);

		if (result == true) {
			return customer;
		} else {
			return null;
		}
	}

	private boolean ValidateMobile(String mobileno) {
		String pattern = "[1-9]{1}[0-9]{9}";
		if (mobileno.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}

	public Customer showBalance(String mobileno) {
		Customer customer = repo.findOne(mobileno);
		if (customer != null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {

		Customer sourceCustomer = repo.findOne(sourceMobileNo);
		Customer targetCustomer = repo.findOne(targetMobileNo);
		Wallet sourceWallet = sourceCustomer.getWallet();
		Wallet targetWallet = targetCustomer.getWallet();
		BigDecimal bal1 = sourceWallet.getBalance().subtract(amount);
		sourceWallet.setBalance(bal1);
		sourceCustomer.setWallet(sourceWallet);
		BigDecimal bal2 = targetWallet.getBalance().add(amount);
		targetWallet.setBalance(bal2);
		targetCustomer.setWallet(targetWallet);
		return sourceCustomer;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		Wallet sourceWallet = customer.getWallet();
		BigDecimal bal = sourceWallet.getBalance().add(amount);
		sourceWallet.setBalance(bal);
		customer.setWallet(sourceWallet);
		return customer;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		Wallet sourceWallet = customer.getWallet();
		BigDecimal bal = sourceWallet.getBalance().subtract(amount);
		sourceWallet.setBalance(bal);
		customer.setWallet(sourceWallet);
		return customer;
	}
}