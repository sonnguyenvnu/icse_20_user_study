@Override public ByteBuf format(){
  Object[] tmp=new Object[]{serverRole.toString(),offset,new Object[0]};
  return new ArrayParser(tmp).format();
}
