public void geodist(byte[] key,byte[] member1,byte[] member2){
  sendCommand(GEODIST,key,member1,member2);
}
