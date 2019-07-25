/** 
 * Modify INVOKEINTERFACE, INVOKESPECIAL, INVOKESTATIC and INVOKEVIRTUAL so that a different method is invoked.  The class name in the operand of these instructions might be a subclass of the target class specified by <code>classname</code>.   This method transforms the instruction in that case unless the subclass overrides the target method.
 */
@Override public int transform(CtClass clazz,int pos,CodeIterator iterator,ConstPool cp) throws BadBytecode {
  int c=iterator.byteAt(pos);
  if (c == INVOKEINTERFACE || c == INVOKESPECIAL || c == INVOKESTATIC || c == INVOKEVIRTUAL) {
    int index=iterator.u16bitAt(pos + 1);
    String cname=cp.eqMember(methodname,methodDescriptor,index);
    if (cname != null && matchClass(cname,clazz.getClassPool())) {
      int ntinfo=cp.getMemberNameAndType(index);
      pos=match(c,pos,iterator,cp.getNameAndTypeDescriptor(ntinfo),cp);
    }
  }
  return pos;
}
