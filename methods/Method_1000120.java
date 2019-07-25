public byte[] encode(){
  if (data.getBytes() == null) {
    return new byte[]{operator.getValue()};
  }
  byte[] r=new byte[1 + data.getBytes().length];
  r[0]=operator.getValue();
  System.arraycopy(data.getBytes(),0,r,1,data.getBytes().length);
  return r;
}
