/** 
 * Load list of time zones if sun.util.calendar.ZoneInfo exists.
 * @return <tt>null</tt> if time zone list cannot be loaded.
 */
private static final String[] loadTimeZoneNames(){
  Class<?> zoneInfo;
  try {
    zoneInfo=Class.forName("sun.util.calendar.ZoneInfo");
  }
 catch (  ClassNotFoundException cnfe) {
    return null;
  }
  Method method;
  try {
    method=zoneInfo.getDeclaredMethod("getAvailableIDs",new Class[0]);
  }
 catch (  NoSuchMethodException nsme) {
    return null;
  }
  Object result;
  try {
    result=method.invoke((Object)null);
  }
 catch (  IllegalAccessException iae) {
    return null;
  }
catch (  InvocationTargetException ite) {
    return null;
  }
  String[] tmpList=(String[])result;
  int numSaved=0;
  String[] finalList=null;
  for (int i=0; i < 2; i++) {
    if (i > 0) {
      if (numSaved == 0) {
        return null;
      }
      finalList=new String[numSaved];
      numSaved=0;
    }
    for (int j=0; j < tmpList.length; j++) {
      final int len=tmpList[j].length();
      if ((len > 2 && Character.isUpperCase(tmpList[j].charAt(1))) && (len != 7 || !Character.isDigit(tmpList[j].charAt(3)))) {
        if (finalList == null) {
          numSaved++;
        }
 else {
          finalList[numSaved++]=tmpList[j];
        }
        if (len == 3 && tmpList[j].charAt(1) == 'S' && tmpList[j].charAt(2) == 'T') {
          if (finalList == null) {
            numSaved++;
          }
 else {
            StringBuffer dst=new StringBuffer();
            dst.append(tmpList[j].charAt(0));
            dst.append("DT");
            finalList[numSaved++]=dst.toString();
          }
        }
      }
    }
  }
  return finalList;
}
