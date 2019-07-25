@Override public void member(String name){
  if (memberName != null || !(stack.peek() instanceof HashSet)) {
    throw new IllegalStateException();
  }
  memberName=name;
  beforeValue();
}
