private String location(){
  String className=dex.typeNames().get(currentClass.getTypeIndex());
  if (currentMethod != null) {
    MethodId methodId=dex.methodIds().get(currentMethod.getMethodIndex());
    return className + "." + dex.strings().get(methodId.getNameIndex());
  }
 else {
    return className;
  }
}
