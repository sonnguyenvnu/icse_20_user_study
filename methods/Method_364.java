public void visitJumpInsn(final int opcode,final Label label){
  if ((label.status & 2) != 0 && label.position - code.length < Short.MIN_VALUE) {
    throw new UnsupportedOperationException();
  }
 else {
    code.putByte(opcode);
    label.put(this,code,code.length - 1);
  }
}
