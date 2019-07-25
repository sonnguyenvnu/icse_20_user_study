/** 
 * Get a named media discoverer. <p> Use  {@link #discoverers(MediaDiscovererCategory)} to get the name.
 * @param name media discoverer name
 * @return media discoverer, may be <code>null</code>
 */
public MediaDiscoverer discoverer(String name){
  libvlc_media_discoverer_t discoverer=libvlc_media_discoverer_new(libvlcInstance,name);
  if (discoverer != null) {
    return MediaDiscovererFactory.newMediaDiscoverer(libvlcInstance,discoverer);
  }
 else {
    return null;
  }
}
