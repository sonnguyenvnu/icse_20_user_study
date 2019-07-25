/** 
 * Clear the entire list.
 * @return <code>true</code> if successful; <code>false</code> if error
 */
public boolean clear(){
  if (!isReadOnly()) {
    lock();
    try {
      int result;
      do {
        result=libvlc_media_list_remove_index(mediaListInstance,0);
      }
 while (result == 0);
      return true;
    }
  finally {
      unlock();
    }
  }
 else {
    return false;
  }
}
