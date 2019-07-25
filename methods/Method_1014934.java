/** 
 * put
 * @param storeKey
 * @param xxlUser
 */
public static void put(String storeKey,XxlSsoUser xxlUser){
  String redisKey=redisKey(storeKey);
  JedisUtil.setObjectValue(redisKey,xxlUser,redisExpireMinite * 60);
}
