public static String getSpecAccessor(SpecModel specModel){
  if (specModel.getSpecElementType() == SpecElementType.KOTLIN_SINGLETON) {
    return specModel.getSpecName() + ".INSTANCE";
  }
  return specModel.getSpecName();
}
