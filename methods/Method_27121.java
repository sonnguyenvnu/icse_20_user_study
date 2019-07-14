public Single<SearchHistory> save(SearchHistory entity){
  return RxHelper.getSingle(App.getInstance().getDataStore().delete(SearchHistory.class).where(SearchHistory.TEXT.eq(entity.getText())).get().single().flatMap(integer -> App.getInstance().getDataStore().insert(entity)));
}
