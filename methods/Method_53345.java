@Override public ResponseList<Category> getSuggestedUserCategories() throws TwitterException {
  return factory.createCategoryList(get(conf.getRestBaseURL() + "users/suggestions.json"));
}
