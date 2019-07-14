@Override public int getCorrectView(IconDataParcelable item,int adapterPosition){
  if (mainFrag.IS_LIST) {
    if (getBoolean(PREFERENCE_SHOW_THUMB)) {
      int filetype=itemsDigested.get(adapterPosition).elem.filetype;
      if (filetype == Icons.VIDEO || filetype == Icons.IMAGE) {
        if (getBoolean(PREFERENCE_USE_CIRCULAR_IMAGES)) {
          return VIEW_PICTURE;
        }
 else {
          return VIEW_APK;
        }
      }
 else       if (filetype == Icons.APK) {
        return VIEW_APK;
      }
    }
    return VIEW_GENERIC;
  }
 else {
    if (item.type == IconDataParcelable.IMAGE_FROMFILE) {
      return VIEW_THUMB;
    }
 else {
      return VIEW_GENERIC;
    }
  }
}
