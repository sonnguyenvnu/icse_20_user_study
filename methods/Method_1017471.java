/** 
 * Setup creates the  {@link Backstack} with the specified initial keys.
 * @param initialKeys the initial keys of the backstack
 */
public void setup(@NonNull List<?> initialKeys){
  core=new NavigationCore(initialKeys);
  core.setBackstack(this);
}
