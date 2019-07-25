/** 
 * Get a named renderer discoverer. <p> Use  {@link #discoverers()} to get the name.
 * @param name discoverer name
 * @return discoverer, may be <code>null</code>
 */
public RendererDiscoverer discoverer(String name){
  libvlc_renderer_discoverer_t discoverer=libvlc_renderer_discoverer_new(libvlcInstance,name);
  if (discoverer != null) {
    return new RendererDiscoverer(discoverer);
  }
 else {
    return null;
  }
}
