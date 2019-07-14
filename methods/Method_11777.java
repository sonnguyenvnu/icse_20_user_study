private List<Class<?>> parseCategories(String categories) throws ClassNotFoundException {
  List<Class<?>> categoryClasses=new ArrayList<Class<?>>();
  for (  String category : categories.split(",")) {
    Class<?> categoryClass=Classes.getClass(category,getClass());
    categoryClasses.add(categoryClass);
  }
  return categoryClasses;
}
