private static boolean isMatchingMethod(String methodName,String[] methodNamePrefixes){
  for (  String methodNamePrefix : methodNamePrefixes) {
    if (methodName.startsWith(methodNamePrefix)) {
      return true;
    }
  }
  return false;
}
