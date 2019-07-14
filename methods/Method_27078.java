@NonNull public static Single<List<Gist>> getMyGists(@NonNull String ownerName){
  return App.getInstance().getDataStore().select(Gist.class).where(Gist.OWNER_NAME.equal(ownerName)).get().observable().toList();
}
