protected void visitLocalVariable(String name,int index){
  if (index >= ignoreCount && index < ignoreCount + paramCount) {
    if (!name.equals("arg" + currentParameter)) {
      debugInfoPresent=true;
    }
    result.append(',');
    result.append(name);
    currentParameter++;
  }
}
