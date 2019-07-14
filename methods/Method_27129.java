public Single<ViewerFile> save(ViewerFile modelEntity){
  return RxHelper.getSingle(App.getInstance().getDataStore().delete(ViewerFile.class).where(ViewerFile.FULL_URL.eq(modelEntity.getFullUrl())).get().single().flatMap(i -> App.getInstance().getDataStore().insert(modelEntity)));
}
