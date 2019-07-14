@Override public @NonNull Observable<User> updateUserSettings(final @NonNull User user){
  this.observable.onNext(Pair.create("update_user_settings",new HashMap<String,Object>(){
{
      put("user",user);
    }
  }
));
  return Observable.just(user);
}
