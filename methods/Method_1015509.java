public static Buffer marshal(LazyRemovalCache<Address,IpAddress> addrs){
  final ByteArrayDataOutputStream out=new ByteArrayDataOutputStream(512);
  try {
    int size=addrs != null ? addrs.size() : 0;
    out.writeInt(size);
    if (size > 0) {
      for (      Map.Entry<Address,LazyRemovalCache.Entry<IpAddress>> entry : addrs.entrySet()) {
        Address key=entry.getKey();
        IpAddress val=entry.getValue().getVal();
        Util.writeAddress(key,out);
        Util.writeStreamable(val,out);
      }
    }
    return out.getBuffer();
  }
 catch (  Exception ex) {
    return null;
  }
}
