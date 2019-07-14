public void visitIincInsn(final int var,final int increment){
  code.putByte(132).put11(var,increment);
}
