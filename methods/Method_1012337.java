/** 
 * Gloading(loading status view) wrap the whole activity wrapper is android.R.id.content
 * @param activity current activity object
 * @return holder of Gloading
 */
public Holder wrap(Activity activity){
  ViewGroup wrapper=activity.findViewById(android.R.id.content);
  return new Holder(mAdapter,activity,wrapper);
}
