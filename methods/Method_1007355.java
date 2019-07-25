@Override public void instrument(ExprEditor editor) throws CannotCompileException {
  checkModify();
  ClassFile cf=getClassFile2();
  List<MethodInfo> methods=cf.getMethods();
  for (  MethodInfo minfo : methods.toArray(new MethodInfo[methods.size()]))   editor.doit(this,minfo);
}
