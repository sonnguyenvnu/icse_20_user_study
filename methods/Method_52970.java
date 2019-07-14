@Override public AccountTotals createAccountTotals(HttpResponse res) throws TwitterException {
  return new LazyAccountTotals(res,factory);
}
