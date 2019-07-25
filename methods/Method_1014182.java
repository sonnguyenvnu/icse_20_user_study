/** 
 * Called whenever the OSGi framework stops our bundle
 */
@Override public void stop(BundleContext bc) throws Exception {
  context=null;
}
