/** 
 * get the city id base on the global region data 
 * @param region
 * @return	int
 */
public int getCityId(String region){
  String[] p=region.split("\\|");
  if (p.length != 5)   return 0;
  String key=null;
  Integer intv=null;
  for (int i=3; i >= 0; i--) {
    if (p[i].equals("0"))     continue;
    if (i == 3 && p[i].indexOf("?????") > -1) {
      key=p[2] + p[3];
    }
 else {
      key=p[i];
    }
    intv=globalRegionMap.get(key);
    if (intv == null)     return 0;
    return intv.intValue();
  }
  return 0;
}
