static boolean isType(TypeMirror type,ClassName className){
  return isType(type,className.reflectionName());
}
