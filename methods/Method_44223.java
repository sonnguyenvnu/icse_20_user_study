/** 
 * @param timeStamp
 * @return
 */
public static Date timeStampToDate(long timeStamp){
  java.util.Date date=new java.util.Date((long)timeStamp * 1000);
  return date;
}
