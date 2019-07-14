@Override public String login(String password) throws TxManagerException {
  if (managerConfig.getAdminKey().equals(password)) {
    String token=RandomUtils.getUUID();
    defaultTokenStorage.add(token);
    return token;
  }
  throw new TxManagerException("password error.");
}
