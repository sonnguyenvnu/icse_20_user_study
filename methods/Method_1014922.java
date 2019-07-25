public boolean connect(String address,String baudrate){
  this.address=address;
  this.baudRate=baudrate;
  return connected=serialForwarder.connect(address,Integer.valueOf(baudrate));
}
