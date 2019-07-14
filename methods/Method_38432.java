/** 
 * Starts bundle usage by creating new  {@link BundleAction}.
 */
public BundleAction start(final String servletPath,final String bundleName){
  return new BundleAction(this,servletPath,bundleName);
}
