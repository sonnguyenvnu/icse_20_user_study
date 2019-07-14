@Override public void getSuggestedUserCategories(){
  getDispatcher().invokeLater(new AsyncTask(SUGGESTED_USER_CATEGORIES,listeners){
    @Override public void invoke(    List<TwitterListener> listeners) throws TwitterException {
      ResponseList<Category> categories=twitter.getSuggestedUserCategories();
      for (      TwitterListener listener : listeners) {
        try {
          listener.gotSuggestedUserCategories(categories);
        }
 catch (        Exception e) {
          logger.warn("Exception at getSuggestedUserCategories",e);
        }
      }
    }
  }
);
}
