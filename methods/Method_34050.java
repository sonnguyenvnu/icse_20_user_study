public void afterPropertiesSet() throws Exception {
  if (getRandom() == null) {
    setRandom(new SecureRandom());
  }
}
