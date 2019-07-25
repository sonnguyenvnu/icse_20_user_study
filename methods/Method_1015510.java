protected Map<Address,IpAddress> unmarshal(byte[] buffer,int offset,int length){
  if (buffer == null)   return null;
  DataInput in=new ByteArrayDataInputStream(buffer,offset,length);
  HashMap<Address,IpAddress> addrs=null;
  try {
    int size=in.readInt();
    if (size > 0) {
      addrs=new HashMap<>(size);
      for (int i=0; i < size; i++) {
        Address key=Util.readAddress(in);
        IpAddress val=Util.readStreamable(IpAddress::new,in);
        addrs.put(key,val);
      }
    }
    return addrs;
  }
 catch (  Exception ex) {
    log.error("%s: failed reading addresses from message: %s",local_addr,ex);
    return null;
  }
}
