protected boolean hasCategory(String category){
  if (category != null) {
    for (    String c : categories) {
      if (category.equalsIgnoreCase(c)) {
        return true;
      }
    }
  }
  return false;
}
