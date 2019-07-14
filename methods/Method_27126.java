@NonNull public static Single<List<User>> getUserFollowerList(@NonNull String following){
  return App.getInstance().getDataStore().select(User.class).where(FOLLOWING_NAME.eq(following)).get().observable().toList();
}
