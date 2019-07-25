@Override public void member(String name){
  if (afterName || stack.peek(-1) != OBJECT) {
    throw new IllegalStateException();
  }
  afterName=true;
  beforeValue();
  encodeString(baos,name).write(':');
}
