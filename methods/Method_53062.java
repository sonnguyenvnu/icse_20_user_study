@Override public ResponseList<Category> createCategoryList(HttpResponse res) throws TwitterException {
  return CategoryJSONImpl.createCategoriesList(res,conf);
}
