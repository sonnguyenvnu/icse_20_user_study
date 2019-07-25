public synchronized String decrypt(String message){
  return new String((new BigInteger(message)).modPow(privateKey,modulus).toByteArray());
}
