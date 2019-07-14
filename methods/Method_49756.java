@Override public String toString(){
  return (address == null ? "" : (address.getHostAddress() + "/" + prefixLength));
}
