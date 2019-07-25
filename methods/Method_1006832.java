/** 
 * FIXME {hash tag}
 */
@SuppressWarnings("rawtypes") @Override public void set(Class clz,String key,Object obj){
  key=getSimpleKey(clz,key);
  int validSecond=getValidSecondAdjusted();
  JedisConnector_Cache.getInstance().set(key,JsonX.toJson(obj),validSecond);
}
