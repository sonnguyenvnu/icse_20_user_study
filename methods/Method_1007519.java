public void fill(T[] msg){
  for (int i=0; i < msg.length; i++) {
    msg[i]=getnb();
    if (msg[i] == null) {
      break;
    }
  }
}
