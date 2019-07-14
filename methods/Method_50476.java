@Override public List<HmilyTransaction> listAll(){
  try {
    List<HmilyTransaction> transactions=Lists.newArrayList();
    Set<byte[]> keys=jedisClient.keys((keyPrefix + "*").getBytes());
    for (    final byte[] key : keys) {
      byte[] contents=jedisClient.get(key);
      if (contents != null) {
        transactions.add(RepositoryConvertUtils.transformBean(contents,objectSerializer));
      }
    }
    return transactions;
  }
 catch (  Exception e) {
    throw new HmilyRuntimeException(e);
  }
}
