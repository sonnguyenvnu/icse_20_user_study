/** 
 * Visits each bytecode in the given range. 
 */
boolean doit(CtClass clazz,MethodInfo minfo,LoopContext context,CodeIterator iterator,int endPos) throws CannotCompileException {
  boolean edited=false;
  while (iterator.hasNext() && iterator.lookAhead() < endPos) {
    int size=iterator.getCodeLength();
    if (loopBody(iterator,clazz,minfo,context)) {
      edited=true;
      int size2=iterator.getCodeLength();
      if (size != size2)       endPos+=size2 - size;
    }
  }
  return edited;
}
