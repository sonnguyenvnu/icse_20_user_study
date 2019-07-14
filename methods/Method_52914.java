private boolean containsAny(final String text,final String[] formatNameVariations){
  for (  final String formattedName : formatNameVariations) {
    if (text.contains(formattedName)) {
      return true;
    }
  }
  return false;
}
