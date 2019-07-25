@Override public void instrument(CodeConverter converter) throws CannotCompileException {
  checkModify();
  ClassFile cf=getClassFile2();
  ConstPool cp=cf.getConstPool();
  List<MethodInfo> methods=cf.getMethods();
  for (  MethodInfo minfo : methods.toArray(new MethodInfo[methods.size()]))   converter.doit(this,minfo,cp);
}
