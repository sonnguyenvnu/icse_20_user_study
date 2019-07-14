protected Set<String> getCategories(Contribution.Filter filter){
  Set<String> outgoing=new HashSet<>();
  Set<String> categorySet=librariesByCategory.keySet();
  for (  String categoryName : categorySet) {
    for (    Contribution contrib : librariesByCategory.get(categoryName)) {
      if (filter.matches(contrib)) {
        if (categoryName != null && !categoryName.trim().isEmpty()) {
          outgoing.add(categoryName);
        }
        break;
      }
    }
  }
  return outgoing;
}
