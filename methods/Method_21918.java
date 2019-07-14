/** 
 * ?????????.
 * @param driver ????????
 * @param url ???URL
 * @param username ??????
 * @param password ?????
 * @return ???????
 */
public static EventTraceDataSource createEventTraceDataSource(final String driver,final String url,final String username,final Optional<String> password){
  Hasher hasher=Hashing.md5().newHasher().putString(driver,Charsets.UTF_8).putString(url,Charsets.UTF_8);
  if (!Strings.isNullOrEmpty(username)) {
    hasher.putString(username,Charsets.UTF_8);
  }
  if (password.isPresent()) {
    hasher.putString(password.get(),Charsets.UTF_8);
  }
  HashCode hashCode=hasher.hash();
  EventTraceDataSource result=DATA_SOURCE_REGISTRY.get(hashCode);
  if (null != result) {
    return result;
  }
  EventTraceDataSourceConfiguration eventTraceDataSourceConfiguration=new EventTraceDataSourceConfiguration(driver,url,username);
  if (password.isPresent()) {
    eventTraceDataSourceConfiguration.setPassword(password.get());
  }
  result=new EventTraceDataSource(eventTraceDataSourceConfiguration);
  result.init();
  DATA_SOURCE_REGISTRY.put(hashCode,result);
  return result;
}
