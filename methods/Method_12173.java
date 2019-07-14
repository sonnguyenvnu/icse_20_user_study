/** 
 * @param states ?????
 * @param value  ???????
 * @return ?????
 */
public static long removeState(long states,long value){
  if (!hasState(states,value)) {
    return states;
  }
  return states ^ value;
}
