//Abu Koroma
// 1908-111-3

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;


public class BloomFilterSet implements Set {
	public int m; //size of Bitset
	private BitSet b;
	
	public BloomFilterSet(int m) {
		this.m = m;
		b = new BitSet();
	}
	
	public int stringHash(String element) {
		return Math.abs(element.hashCode()) % m;
	}
	
	
	public int myHash(String element) {
		int output = (int) Math.abs(element.length() * Math.pow(element.lastIndexOf("e"), 2) + 
				element.compareTo("Potato")) % m;
		return output;
	}
	
	public int messageDigestHash(String element) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(element.getBytes());
			MessageDigest dig = (MessageDigest) md.clone();
			byte[] digest = dig.digest();
			int output = Math.abs(ByteBuffer.wrap(digest).getInt()) % m;
			return output;
		} catch (CloneNotSupportedException cnse) {
			cnse.printStackTrace();
			return -1;
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return -1;
		}
	}
	

	@Override
	public void add(String element) { //divide added element % m
		b.set(stringHash(element) % m, true);
		b.set(messageDigestHash(element) % m, true);
		b.set(myHash(element) % m, true);
	}

	@Override
	public boolean contains(String element) {
		int index = stringHash(element);
		int index2 = messageDigestHash(element);
		int index3 = myHash(element);
		return b.get(index) && b.get(index2) && b.get(index3);
	}

}
