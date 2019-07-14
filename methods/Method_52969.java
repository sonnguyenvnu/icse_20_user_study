@Override public ResponseList<Category> createCategoryList(final HttpResponse res) throws TwitterException {
  return new LazyResponseList<Category>(){
    @Override protected ResponseList<Category> createActualResponseList() throws TwitterException {
      return CategoryJSONImpl.createCategoriesList(res,conf);
    }
  }
;
}
