public static void deleteAll(){
  App.getInstance().getDataStore().delete(SearchHistory.class).get().value();
}
