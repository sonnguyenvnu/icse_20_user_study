/** 
 * Performs code conversion.
 */
protected void doit(CtClass clazz,MethodInfo minfo,ConstPool cp) throws CannotCompileException {
  Transformer t;
  CodeAttribute codeAttr=minfo.getCodeAttribute();
  if (codeAttr == null || transformers == null)   return;
  for (t=transformers; t != null; t=t.getNext())   t.initialize(cp,clazz,minfo);
  CodeIterator iterator=codeAttr.iterator();
  while (iterator.hasNext()) {
    try {
      int pos=iterator.next();
      for (t=transformers; t != null; t=t.getNext())       pos=t.transform(clazz,pos,iterator,cp);
    }
 catch (    BadBytecode e) {
      throw new CannotCompileException(e);
    }
  }
  int locals=0;
  int stack=0;
  for (t=transformers; t != null; t=t.getNext()) {
    int s=t.extraLocals();
    if (s > locals)     locals=s;
    s=t.extraStack();
    if (s > stack)     stack=s;
  }
  for (t=transformers; t != null; t=t.getNext())   t.clean();
  if (locals > 0)   codeAttr.setMaxLocals(codeAttr.getMaxLocals() + locals);
  if (stack > 0)   codeAttr.setMaxStack(codeAttr.getMaxStack() + stack);
  try {
    minfo.rebuildStackMapIf6(clazz.getClassPool(),clazz.getClassFile2());
  }
 catch (  BadBytecode b) {
    throw new CannotCompileException(b.getMessage(),b);
  }
}
