@Override public void visitAnnotableParameterCount(final int parameterCount,final boolean visible){
  if (visible) {
    visibleAnnotableParameterCount=parameterCount;
  }
 else {
    invisibleAnnotableParameterCount=parameterCount;
  }
}
