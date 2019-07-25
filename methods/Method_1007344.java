@Override public int transform(CtClass tclazz,int pos,CodeIterator iterator,ConstPool cp) throws BadBytecode {
  int c=iterator.byteAt(pos);
  if (c == GETFIELD || c == GETSTATIC) {
    int index=iterator.u16bitAt(pos + 1);
    String typedesc=isField(tclazz.getClassPool(),cp,fieldClass,fieldname,isPrivate,index);
    if (typedesc != null) {
      if (c == GETSTATIC) {
        iterator.move(pos);
        pos=iterator.insertGap(1);
        iterator.writeByte(ACONST_NULL,pos);
        pos=iterator.next();
      }
      String type="(Ljava/lang/Object;)" + typedesc;
      int mi=cp.addClassInfo(methodClassname);
      int methodref=cp.addMethodrefInfo(mi,methodName,type);
      iterator.writeByte(INVOKESTATIC,pos);
      iterator.write16bit(methodref,pos + 1);
      return pos;
    }
  }
  return pos;
}
