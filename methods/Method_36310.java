/** 
 * refer to  {@link HealthIndicatorNameFactory#apply(String)}
 */
public String getKey(String name){
  int index=name.toLowerCase().indexOf("healthindicator");
  if (index > 0) {
    return name.substring(0,index);
  }
  return name;
}
