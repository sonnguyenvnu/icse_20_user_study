public void write(byte b[],int offset,int length){
  this.messageConsumer.message(new String(b,offset,length));
}
