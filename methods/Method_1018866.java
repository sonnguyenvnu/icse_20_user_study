/** 
 * Get the media resource locator for the current media.
 * @return media resource locator
 */
public String mrl(){
  return NativeString.copyAndFreeNativeString(libvlc_media_get_mrl(mediaInstance));
}
