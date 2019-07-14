/** 
 * ??????.
 * @param connectString ?????????
 * @param namespace ????????
 * @param digest ??????
 * @return ??????
 */
public static CoordinatorRegistryCenter createCoordinatorRegistryCenter(final String connectString,final String namespace,final Optional<String> digest){
  Hasher hasher=Hashing.md5().newHasher().putString(connectString,Charsets.UTF_8).putString(namespace,Charsets.UTF_8);
  if (digest.isPresent()) {
    hasher.putString(digest.get(),Charsets.UTF_8);
  }
  HashCode hashCode=hasher.hash();
  CoordinatorRegistryCenter result=REG_CENTER_REGISTRY.get(hashCode);
  if (null != result) {
    return result;
  }
  ZookeeperConfiguration zkConfig=new ZookeeperConfiguration(connectString,namespace);
  if (digest.isPresent()) {
    zkConfig.setDigest(digest.get());
  }
  result=new ZookeeperRegistryCenter(zkConfig);
  result.init();
  REG_CENTER_REGISTRY.put(hashCode,result);
  return result;
}
