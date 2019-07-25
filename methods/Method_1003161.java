@Override public void write(WriteBuffer buff,Object obj){
  String s=obj.toString();
  int len=s.length();
  buff.putVarInt(len).putStringData(s,len);
}
