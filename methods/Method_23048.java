synchronized void setSocket(Socket s){
  this.s=s;
  notify();
}
