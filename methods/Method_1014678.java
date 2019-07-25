public synchronized String encrypt(String message){
  return (new BigInteger(message.getBytes())).modPow(publicKey,modulus).toString();
}
