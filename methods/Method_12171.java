/** 
 * @param states ?????
 * @param value  ???????
 * @return ????
 */
public static boolean hasState(long states,long value){
  return (states & value) != 0;
}
