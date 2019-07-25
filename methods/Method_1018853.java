/** 
 * Get the list of media discoverer descriptions for a particular category.
 * @param category desired category
 * @return media discoverer descriptions, will not be <code>null</code>
 */
public List<MediaDiscovererDescription> discoverers(MediaDiscovererCategory category){
  PointerByReference ref=new PointerByReference();
  size_t size=libvlc_media_discoverer_list_get(libvlcInstance,category.intValue(),ref);
  try {
    int count=size.intValue();
    List<MediaDiscovererDescription> result=new ArrayList<MediaDiscovererDescription>(count);
    if (count > 0) {
      Pointer[] pointers=ref.getValue().getPointerArray(0,count);
      for (      Pointer pointer : pointers) {
        libvlc_media_discoverer_description_t description=Structure.newInstance(libvlc_media_discoverer_description_t.class,pointer);
        description.read();
        result.add(new MediaDiscovererDescription(description.psz_name,description.psz_longname,MediaDiscovererCategory.mediaDiscovererCategory(description.i_cat)));
      }
    }
    return result;
  }
  finally {
    libvlc_renderer_discoverer_list_release(ref.getValue(),size);
  }
}
