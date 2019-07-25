public void put(Message msg){
  put(Sha256Hash.of(msg.getData()),msg);
}
