static String capitalizeFirstLetter(String original){
  if (original == null || original.isEmpty()) {
    return original;
  }
  return original.substring(0,1).toUpperCase() + original.substring(1);
}
