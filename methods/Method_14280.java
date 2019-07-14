/** 
 * Get name of current place in time.
 * @param place place ID
 * @return place name (<tt>"hour"</tt>, <tt>"minute"</tt>, etc.
 */
private static final String getTimePlaceString(int place){
switch (place) {
case PLACE_HOUR:
    return "hour";
case PLACE_MINUTE:
  return "minute";
case PLACE_SECOND:
return "second";
case PLACE_MILLI:
return "millisecond";
default :
break;
}
return "unknown";
}
