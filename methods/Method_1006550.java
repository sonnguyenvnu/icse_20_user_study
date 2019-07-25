private static boolean find(String interfaceName,TypeElement typeElement,Set<Element> visited){
  if (visited.contains(typeElement)) {
    return false;
  }
  visited.add(typeElement);
  if (typeElement.getQualifiedName().contentEquals(interfaceName)) {
    return true;
  }
  for (  TypeMirror implemented : typeElement.getInterfaces()) {
    if (find(interfaceName,(TypeElement)((DeclaredType)implemented).asElement())) {
      return true;
    }
  }
  while (typeElement.getSuperclass().getKind() != TypeKind.NONE) {
    typeElement=(TypeElement)((DeclaredType)typeElement.getSuperclass()).asElement();
    if (find(interfaceName,typeElement)) {
      return true;
    }
  }
  return false;
}
