@Override protected LoadType<Account> load(){
  return ofy().load().type(Account.class);
}
