public void pre(){
  if (serialAvailableMethod != null && invokeSerialAvailable) {
    invokeSerialAvailable=false;
    try {
      serialAvailableMethod.invoke(parent,this);
    }
 catch (    Exception e) {
      System.err.println("Error, disabling serialAvailable() for " + port.getPortName());
      System.err.println(e.getLocalizedMessage());
      serialAvailableMethod=null;
    }
  }
}
