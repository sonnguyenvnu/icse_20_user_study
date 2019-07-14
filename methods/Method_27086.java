public static Login getUser(@NonNull String login){
  return App.getInstance().getDataStore().select(Login.class).where(Login.LOGIN.eq(login).and(Login.TOKEN.notNull())).get().firstOrNull();
}
