package repo;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import beans.Customer;

public class WalletRepoImpl implements WalletRepo {
	private Map<String, Customer> data; 
	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public boolean save(Customer customer) {
		if(customer==null)
		{
			return false;
		}
		else
		{
			data.put(customer.getMobileNo(), customer);
		return true;
		}
		
	}

	public Customer findOne(String mobileNo) {
		
		Set s=data.entrySet();
		Customer customer=null;
		Iterator iter=s.iterator();
		while(iter.hasNext())
		{
			Map.Entry map=(Entry) iter.next();
			String mobile=(String) map.getKey();
			if(mobile.equals(mobileNo))
			{
				customer=(Customer)map.getKey();
			}
		}
		return customer;
	}


}
