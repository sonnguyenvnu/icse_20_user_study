/** 
 * Allow or disallow set of file extensions. Only one rule can be active at time, which means user can only specify extensions that are either allowed or disallowed. Setting this value to <code>null</code> will turn this feature off.
 */
public AdaptiveFileUploadFactory setFileExtensions(final String[] fileExtensions,final boolean allow){
  this.fileExtensions=fileExtensions;
  this.allowFileExtensions=allow;
  return this;
}
