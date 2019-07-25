@Override public void member(String name){
  if (memberName != null || !(stack.peek() instanceof JSONObject)) {
    throw new IllegalStateException();
  }
  memberName=name;
  beforeValue();
}
