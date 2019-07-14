/** 
 * @return the list of categories that this contribution is part of(e.g. "Typography / Geometry"). "Unknown" if the category null.
 */
static StringList parseCategories(StringDict properties){
  StringList outgoing=new StringList();
  String categoryStr=properties.get(CATEGORIES_PROPERTY);
  if (categoryStr == null) {
    categoryStr=properties.get("category");
  }
  if (categoryStr != null) {
    String[] listing=PApplet.trim(PApplet.split(categoryStr,','));
    for (    String category : listing) {
      if (validCategories.contains(category)) {
        category=translateCategory(category);
        outgoing.append(category);
      }
    }
  }
  if (outgoing.size() == 0) {
    return unknownCategoryList();
  }
  return outgoing;
}
