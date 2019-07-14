/** 
 * Returns true if the check is valid, false if not
 * @param sectionLocationInfo
 * @param index
 * @return
 */
@UiThread private boolean isFocusValid(SectionLocationInfo sectionLocationInfo,int index){
  if (index >= sectionLocationInfo.mSection.getCount() || index < 0) {
    String errorMessage="You are trying to request focus with offset on an index that is out of bounds: " + "requested " + index + " , total " + sectionLocationInfo.mSection.getCount();
    if (mContext != null && mContext.getLogger() != null) {
      mContext.getLogger().emitMessage(ERROR,errorMessage);
    }
 else {
      Log.e(SectionsDebug.TAG,errorMessage);
    }
    return false;
  }
  return true;
}
