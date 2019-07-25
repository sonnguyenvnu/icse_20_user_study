@Override public Xid[] recover(int flags) throws XAException {
  XAReturnValue r=DTC_XA_Interface(XA_RECOVER,null,flags | tightlyCoupled);
  int offset=0;
  ArrayList<XidImpl> al=new ArrayList<>();
  if (null == r.bData)   return new XidImpl[0];
  while (offset < r.bData.length) {
    int power=1;
    int formatId=0;
    for (int i=0; i < 4; i++) {
      int x=(r.bData[offset + i] & 0x00FF);
      x=x * power;
      formatId+=x;
      power=power * 256;
    }
    offset+=4;
    int gid_len=(r.bData[offset++] & 0x00FF);
    int bid_len=(r.bData[offset++] & 0x00FF);
    byte gid[]=new byte[gid_len];
    byte bid[]=new byte[bid_len];
    System.arraycopy(r.bData,offset,gid,0,gid_len);
    offset+=gid_len;
    System.arraycopy(r.bData,offset,bid,0,bid_len);
    offset+=bid_len;
    XidImpl xid=new XidImpl(formatId,gid,bid);
    al.add(xid);
  }
  XidImpl xids[]=new XidImpl[al.size()];
  for (int i=0; i < al.size(); i++) {
    xids[i]=al.get(i);
    if (xaLogger.isLoggable(Level.FINER))     xaLogger.finer(toString() + xids[i].toString());
  }
  return xids;
}
