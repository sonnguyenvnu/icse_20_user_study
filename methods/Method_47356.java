/** 
 * Recycle after usage, to avoid getting inconsistent result because of static modifiers
 */
public static void recycle(){
  lastConfiguration=new Configuration();
  lastDensity=-1;
}
