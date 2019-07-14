@NonNull public static Single<List<Gist>> getGists(){
  return App.getInstance().getDataStore().select(Gist.class).where(Gist.OWNER_NAME.isNull()).get().observable().toList();
}
