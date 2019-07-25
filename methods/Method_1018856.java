/** 
 * Get the list of renderer discoverer descriptions.
 * @return descriptions, will not be <code>null</code>
 */
public List<RendererDiscovererDescription> discoverers(){
  PointerByReference ref=new PointerByReference();
  size_t size=libvlc_renderer_discoverer_list_get(libvlcInstance,ref);
  try {
    int count=size.intValue();
    List<RendererDiscovererDescription> result=new ArrayList<RendererDiscovererDescription>(count);
    if (count > 0) {
      Pointer[] pointers=ref.getValue().getPointerArray(0,count);
      for (      Pointer pointer : pointers) {
        libvlc_rd_description_t description=Structure.newInstance(libvlc_rd_description_t.class,pointer);
        description.read();
        result.add(new RendererDiscovererDescription(description.psz_name,description.psz_longname));
      }
    }
    return result;
  }
  finally {
    libvlc_renderer_discoverer_list_release(ref.getValue(),size);
  }
}
