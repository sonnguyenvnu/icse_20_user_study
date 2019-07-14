/** 
 * @param states ?????
 * @param value  ???????
 * @return ?????
 */
public static long addState(long states,long value){
  if (hasState(states,value)) {
    return states;
  }
  return (states | value);
}
