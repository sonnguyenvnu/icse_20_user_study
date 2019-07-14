public static boolean hasNormalLogin(){
  return App.getInstance().getDataStore().count(Login.class).where(Login.IS_ENTERPRISE.eq(false).or(Login.IS_ENTERPRISE.isNull())).get().value() > 0;
}
