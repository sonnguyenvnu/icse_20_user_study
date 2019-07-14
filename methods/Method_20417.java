static Element getElementByName(String name,Elements elements,Types types){
  try {
    return elements.getTypeElement(name);
  }
 catch (  MirroredTypeException mte) {
    return types.asElement(mte.getTypeMirror());
  }
}
