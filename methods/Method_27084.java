public void save(Login entity){
  App.getInstance().getDataStore().delete(Login.class).where(Login.LOGIN.eq(entity.getLogin())).get().single().flatMap(integer -> App.getInstance().getDataStore().insert(entity)).blockingGet();
}
