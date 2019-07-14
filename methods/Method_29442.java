/** 
 * cron??????????hostId????????
 * @param hostId
 * @return
 */
public static String getHourCronByHostId(long hostId){
  String hourCron="0 %s 0/1 ? * *";
  Random random=new Random();
  long minute=(hostId + random.nextInt(Integer.MAX_VALUE)) % 60;
  return String.format(hourCron,minute);
}
