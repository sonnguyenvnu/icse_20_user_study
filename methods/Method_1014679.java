public synchronized BigInteger encrypt(BigInteger message){
  return message.modPow(publicKey,modulus);
}
