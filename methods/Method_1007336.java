@Override protected int match(int c,int pos,CodeIterator iterator,int typedesc,ConstPool cp) throws BadBytecode {
  if (newIndex == 0) {
    String desc=Descriptor.ofParameters(parameterTypes) + 'V';
    desc=Descriptor.insertParameter(classname,desc);
    int nt=cp.addNameAndTypeInfo(newMethodname,desc);
    int ci=cp.addClassInfo(newClassname);
    newIndex=cp.addMethodrefInfo(ci,nt);
    constPool=cp;
  }
  if (saveCode == null)   makeCode(parameterTypes,cp);
  return match2(pos,iterator);
}
