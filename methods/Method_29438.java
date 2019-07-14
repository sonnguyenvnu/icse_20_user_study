/** 
 * cron??????????appId???????
 * @param appId
 * @return
 */
public static String getMinuteCronByAppId(long appId){
  String baseCron=(appId % 50) + " 0/1 * ? * *";
  return baseCron;
}
