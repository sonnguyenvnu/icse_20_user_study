public void unsubscribe(@NotNull ClientToken token){
  if (myActiveClients.remove(token)) {
    token.deactivate();
    myInactiveClients.add(token);
  }
}
