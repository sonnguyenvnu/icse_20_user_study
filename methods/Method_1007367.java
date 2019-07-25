/** 
 * Undocumented method.  Do not use; internal-use only.
 */
public boolean doit(CtClass clazz,MethodInfo minfo) throws CannotCompileException {
  CodeAttribute codeAttr=minfo.getCodeAttribute();
  if (codeAttr == null)   return false;
  CodeIterator iterator=codeAttr.iterator();
  boolean edited=false;
  LoopContext context=new LoopContext(codeAttr.getMaxLocals());
  while (iterator.hasNext())   if (loopBody(iterator,clazz,minfo,context))   edited=true;
  ExceptionTable et=codeAttr.getExceptionTable();
  int n=et.size();
  for (int i=0; i < n; ++i) {
    Handler h=new Handler(et,i,iterator,clazz,minfo);
    edit(h);
    if (h.edited()) {
      edited=true;
      context.updateMax(h.locals(),h.stack());
    }
  }
  if (codeAttr.getMaxLocals() < context.maxLocals)   codeAttr.setMaxLocals(context.maxLocals);
  codeAttr.setMaxStack(codeAttr.getMaxStack() + context.maxStack);
  try {
    if (edited)     minfo.rebuildStackMapIf6(clazz.getClassPool(),clazz.getClassFile2());
  }
 catch (  BadBytecode b) {
    throw new CannotCompileException(b.getMessage(),b);
  }
  return edited;
}
