public static Login getUser(){
  return App.getInstance().getDataStore().select(Login.class).where(Login.LOGIN.notNull().and(Login.TOKEN.notNull()).and(Login.IS_LOGGED_IN.eq(true))).get().firstOrNull();
}
