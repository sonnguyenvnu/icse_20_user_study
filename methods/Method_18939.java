public static boolean isTypeElement(final SpecModel specModel){
  final Object representedObject=specModel.getRepresentedObject();
  return representedObject instanceof TypeElement;
}
