public void visitLdcInsn(final Object cst){
  Item i=cw.newConstItem(cst);
  int index=i.index;
  if (i.type == 5 || i.type == 6) {
    code.put12(20,index);
  }
 else   if (index >= 256) {
    code.put12(19,index);
  }
 else {
    code.put11(18,index);
  }
}
