@Override public ByteBuf format(){
  Object[] tmp=new Object[]{serverRole.toString(),masterHost,masterPort,masterState.getDesc(),masterOffset};
  return new ArrayParser(tmp).format();
}
