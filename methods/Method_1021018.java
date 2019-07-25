/** 
 * ???? key ??????? ???? key ???????
 */
@SuppressWarnings("rawtypes") public Set smembers(Object key){
  Set<byte[]> data=jedisCluster.smembers(keyToBytes(key));
  Set<Object> result=new HashSet<Object>();
  valueSetFromBytesSet(data,result);
  return result;
}
