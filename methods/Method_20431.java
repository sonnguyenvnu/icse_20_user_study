static String decapitalizeFirstLetter(String original){
  if (original == null || original.isEmpty()) {
    return original;
  }
  return original.substring(0,1).toLowerCase() + original.substring(1);
}
