@Override public int length(){
  if (addressPlainText) {
    return address.getHostAddress().length();
  }
 else {
    return INET4_ADDRESS_SIZE_IN_BYTES;
  }
}
