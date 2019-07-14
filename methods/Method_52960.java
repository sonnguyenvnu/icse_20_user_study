private AccountSettings getTarget(){
  if (target == null) {
    try {
      target=factory.createAccountSettings(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
