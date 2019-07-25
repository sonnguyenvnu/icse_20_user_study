/** 
 * Prepare new media (set it, do not play it).
 * @param mrl media resource locator
 * @param options zero or more options to attach to the new media
 * @return <code>true</code> if successful; <code>false</code> on error
 */
public boolean prepare(String mrl,String... options){
  return changeMedia(MediaFactory.newMedia(libvlcInstance,mrl,options));
}
