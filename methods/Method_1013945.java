/** 
 * This method is called when all of the services required by this factory are available.
 * @param bundleContext - the {@link ComponentContext} of the HandlerFactory component.
 */
@Activate protected void activate(BundleContext bundleContext){
  this.bundleContext=bundleContext;
}
