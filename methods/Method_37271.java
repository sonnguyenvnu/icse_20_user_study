/** 
 * Static ctor.
 */
public static BeanWalker walk(final BeanWalkerCallback callback){
  return new BeanWalker(callback);
}
