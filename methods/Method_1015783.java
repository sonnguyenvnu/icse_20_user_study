/** 
 * Method copied from http://www.jboss.com/index.html?module=bb&op=viewtopic&t=77231 
 */
public static String print(short version){
  int major=(version & MAJOR_MASK) >> MAJOR_SHIFT;
  int minor=(version & MINOR_MASK) >> MINOR_SHIFT;
  int micro=(version & MICRO_MASK);
  return major + "." + minor + "." + micro;
}
