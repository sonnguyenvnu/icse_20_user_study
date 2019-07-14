private AccountTotals getTarget(){
  if (target == null) {
    try {
      target=factory.createAccountTotals(res);
    }
 catch (    TwitterException e) {
      throw new TwitterRuntimeException(e);
    }
  }
  return target;
}
