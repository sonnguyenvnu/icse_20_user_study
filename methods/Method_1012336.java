/** 
 * Create a new Gloading different from the default one
 * @param adapter another adapter different from the default one
 * @return Gloading
 */
public static Gloading from(Adapter adapter){
  Gloading gloading=new Gloading();
  gloading.mAdapter=adapter;
  return gloading;
}
