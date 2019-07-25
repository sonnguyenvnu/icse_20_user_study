/** 
 * Get the media resource locator for a particular item in the list.
 * @param index item index
 * @return media resource locator
 */
public String mrl(int index){
  lock();
  try {
    libvlc_media_t media=libvlc_media_list_item_at_index(mediaListInstance,index);
    if (media != null) {
      try {
        return NativeString.copyNativeString(libvlc_media_get_mrl(media));
      }
  finally {
        libvlc_media_release(media);
      }
    }
 else {
      return null;
    }
  }
  finally {
    unlock();
  }
}
