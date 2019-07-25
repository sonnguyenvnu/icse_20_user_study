protected static Buffer marshal(Collection<? extends Address> mbrs){
  try {
    final ByteArrayDataOutputStream out=new ByteArrayDataOutputStream((int)Util.size(mbrs));
    Util.writeAddresses(mbrs,out);
    return out.getBuffer();
  }
 catch (  Exception ex) {
    return null;
  }
}
