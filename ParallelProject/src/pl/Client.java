package pl;

import java.util.List;
import java.util.Scanner;

import services.WalletService;
import services.WalletServiceImpl;
import java.math.BigDecimal;
import beans.Customer;

public class Client 
{
	public static void main(String[] args) {
		
		WalletService service;
		{
		service=new WalletServiceImpl();
		}
		List myList;
		
		Scanner sc= new Scanner(System.in);
		String ans;
		int no;
		
		do{
			System.out.println("*****WALLET Services*****");
			System.out.println("1.create Account");
			System.out.println("2.Display Balance");
			System.out.println("3.fund transfer");
			System.out.println("4.Deposit");
			System.out.println("5.withdraw");
			
			System.out.println("Pls enter your choice: ");
			no = sc.nextInt();
			switch(no)
			{
			case 1:
				System.out.println("*****Enter wallet details*****\n");
				System.out.println("Enter the name");
				String name=sc.next();
				System.out.println("Enter the mobile number");
				String mobile=sc.next();
				System.out.println("Enter the amount");
				BigDecimal amount=sc.nextBigDecimal();
				Customer customer=service.createAccount(name, mobile, amount);
				System.out.println(customer);
				break;
				
			case 2:
				System.out.println("enter the mobile number for which you want customer wallet balance");
				String mobile1=sc.next();
				Customer customer2=service.showBalance(mobile1);
				System.out.println(customer2);
				break;
				
			case 3:
				System.out.println("enter the mobile number");
				String sourceMobileNo=sc.next();
				System.out.println("enter the mobile number");
				String targetMobileNo=sc.next();
				System.out.println("enter the amount");
				BigDecimal amount3=sc.nextBigDecimal();
				Customer customer3=service.fundTransfer(sourceMobileNo, targetMobileNo, amount3);
				System.out.println(customer3);
				break;
				
			case 4:
				System.out.println("enter the mobile number to which amount has to be sent");
				String mobileno2=sc.next();
				System.out.println("enter the amount");
				BigDecimal amount4=sc.nextBigDecimal();
				Customer customer4=service.depositAmount(mobileno2, amount4);
				System.out.println(customer4);
				break;
				
			case 5:
				System.out.println("enter the mobile number from which you want to withdraw money");
				String mobileno3=sc.next();
				System.out.println("enter the amount");
				BigDecimal amount5=sc.nextBigDecimal();
				Customer customer5=service.withdrawAmount(mobileno3, amount5);
				break;
				
				
				default:
					System.exit(0);
					break;
			}
			System.out.println("do you want to continue");
			ans=sc.next();
		}
		while (ans.equals("yes")||ans.equals("y")||ans.equals("yes"));
	}
				
			}
		
