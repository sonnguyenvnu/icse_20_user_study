@Override public void write(WriteBuffer out,Short object){
  out.putShort((short)(object - Short.MIN_VALUE));
}
