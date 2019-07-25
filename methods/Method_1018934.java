/** 
 * Prepare new media (set it, do not play it). <p> The supplied  {@link MediaRef} is not kept by this component and <em>must</em> be released by the caller when thecaller no longer has any use for it.
 * @param mediaRef media reference
 * @param options zero or more options to attach to the new media
 * @return <code>true</code> if successful; <code>false</code> on error
 */
public boolean prepare(MediaRef mediaRef,String... options){
  return changeMedia(MediaFactory.newMedia(libvlcInstance,mediaRef,options));
}
