public static Disposable saveUserFollowingList(@NonNull List<User> models,@NonNull String followerName){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      Login login=Login.getUser();
      if (login != null) {
        BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
        if (login.getLogin().equalsIgnoreCase(followerName)) {
          dataSource.delete(User.class).where(FOLLOWER_NAME.eq(followerName)).get().value();
          if (!models.isEmpty()) {
            for (            User user : models) {
              dataSource.delete(User.class).where(User.ID.eq(user.getId())).get().value();
              user.setFollowerName(followerName);
              dataSource.insert(user);
            }
          }
        }
 else {
          dataSource.delete(User.class).where(User.FOLLOWER_NAME.notEqual(login.getLogin())).get().value();
        }
      }
      s.onNext("");
    }
 catch (    Exception e) {
      s.onError(e);
    }
    s.onComplete();
  }
)).subscribe(o -> {
  }
,Throwable::printStackTrace);
}
