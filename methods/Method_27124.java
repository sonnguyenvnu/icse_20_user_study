public static Disposable saveUserFollowerList(@NonNull List<User> models,@NonNull String followingName){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      Login login=Login.getUser();
      if (login != null) {
        BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
        if (login.getLogin().equalsIgnoreCase(followingName)) {
          dataSource.delete(User.class).where(FOLLOWING_NAME.eq(followingName)).get().value();
          if (!models.isEmpty()) {
            for (            User user : models) {
              dataSource.delete(User.class).where(User.ID.eq(user.getId())).get().value();
              user.setFollowingName(followingName);
              dataSource.insert(user);
            }
          }
        }
 else {
          dataSource.delete(User.class).where(User.FOLLOWING_NAME.notEqual(login.getLogin())).get().value();
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
