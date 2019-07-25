@Override public void initialize(ConstPool cp,CtClass clazz,MethodInfo minfo) throws CannotCompileException {
  CodeIterator iterator=minfo.getCodeAttribute().iterator();
  while (iterator.hasNext()) {
    try {
      int pos=iterator.next();
      int c=iterator.byteAt(pos);
      if (c == AALOAD)       initFrames(clazz,minfo);
      if (c == AALOAD || c == BALOAD || c == CALOAD || c == DALOAD || c == FALOAD || c == IALOAD || c == LALOAD || c == SALOAD) {
        pos=replace(cp,iterator,pos,c,getLoadReplacementSignature(c));
      }
 else       if (c == AASTORE || c == BASTORE || c == CASTORE || c == DASTORE || c == FASTORE || c == IASTORE || c == LASTORE || c == SASTORE) {
        pos=replace(cp,iterator,pos,c,getStoreReplacementSignature(c));
      }
    }
 catch (    Exception e) {
      throw new CannotCompileException(e);
    }
  }
}
