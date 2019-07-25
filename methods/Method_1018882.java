/** 
 * Get a list of media resource locators for the items in the list.
 * @return media resource locators
 */
public List<String> mrls(){
  lock();
  try {
    int count=libvlc_media_list_count(mediaListInstance);
    List<String> result=new ArrayList<String>(count);
    for (int i=0; i < count; i++) {
      libvlc_media_t item=libvlc_media_list_item_at_index(mediaListInstance,i);
      result.add(NativeString.copyNativeString(libvlc_media_get_mrl(item)));
      libvlc_media_release(item);
    }
    return result;
  }
  finally {
    unlock();
  }
}
