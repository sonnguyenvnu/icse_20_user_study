/** 
 * When activating the service, we need to keep the bundle context.
 * @param context the bundle context provided through OSGi DS.
 */
protected void activate(BundleContext context){
  this.context=context;
}
