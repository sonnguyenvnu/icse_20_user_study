@NonNull public static Single<List<User>> getUserFollowingList(@NonNull String follower){
  return App.getInstance().getDataStore().select(User.class).where(FOLLOWER_NAME.eq(follower)).get().observable().toList();
}
