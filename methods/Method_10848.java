/** 
 * GPS?? ??? ?? ?? 113.202222 ??? 113°12?8?
 * @param location
 * @return
 */
public static String gpsToDegree(double location){
  double degree=Math.floor(location);
  double minute_temp=(location - degree) * 60;
  double minute=Math.floor(minute_temp);
  String second=new DecimalFormat("#.##").format((minute_temp - minute) * 60);
  return (int)degree + "°" + (int)minute + "?" + second + "?";
}
