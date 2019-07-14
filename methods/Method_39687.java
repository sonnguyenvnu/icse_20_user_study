@Override public void visitLocalVariable(final String name,final String desc,String signature,final Label start,final Label end,final int index){
  if ((index >= ignoreCount) && (index < (ignoreCount + paramCount))) {
    if (!name.equals("arg" + currentParam)) {
      debugInfoPresent=true;
    }
    if (signature == null) {
      signature=desc;
    }
    methodParameters[currentParam]=new MethodParameter(name,signature,parameters[currentParam]);
    currentParam++;
  }
}
