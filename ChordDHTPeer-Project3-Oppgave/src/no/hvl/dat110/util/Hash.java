package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import  javax.xml.bind.DatatypeConverter;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {


	public static int mdBit= 0;
	private static BigInteger hashint;


	public static BigInteger hashOf(String entity) {
		try {

			// Task: Hash a given string using MD5 and return the result as a BigInteger.

			// we use MD5 with 128 bits digest
			MessageDigest md = MessageDigest.getInstance("MD5");
			// compute the hash of the input 'entity'
			//md.update(entity.getBytes());
			byte[] digest = md.digest(entity.getBytes("utf8"));
			// convert the hash into hex format
			String nyHash = DatatypeConverter.printHexBinary(digest);
			// convert the hex into BigInteger
			hashint = new BigInteger(nyHash, 16);
			// return the BigInteger

			 //mdBit = digest.length();
		}catch (NoSuchAlgorithmException | UnsupportedEncodingException e ) {
			e.printStackTrace();
		}

		return hashint;
	}

	public static BigInteger addressSize() {
int digestLength = 0;
		// Task: compute the address size of MD5
		//BigInteger mdAdressSize = new BigInteger("2");

		// get the digest length
		try {
			MessageDigest md5 =  MessageDigest.getInstance("MD5");
		digestLength = md5.getDigestLength() * 8;
		// compute the number of bits = digest length * 8
		}catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// compute the address size = 2 ^ number of bits
		BigInteger powerOf = new BigInteger("2");
		powerOf = powerOf.pow(digestLength);
		// return the address size
		return powerOf;

	}

	public static int bitSize() {

		int digestlen = 0;

		// find the digest length

		MessageDigest dig;
		try {
			dig = MessageDigest.getInstance("MD5");
			digestlen = dig.getDigestLength();
		}catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return digestlen * 8;
	}


	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}

