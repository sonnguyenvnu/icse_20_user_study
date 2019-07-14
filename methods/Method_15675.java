@Override public void allLoggedUser(Consumer<UserToken> consumer){
  tokenStorage.values().forEach(consumer);
}
