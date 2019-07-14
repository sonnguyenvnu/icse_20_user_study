public static Repo getRepo(long id){
  return App.getInstance().getDataStore().select(Repo.class).where(ID.eq(id)).get().firstOrNull();
}
