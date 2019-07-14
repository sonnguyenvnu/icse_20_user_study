public String getEndSync(){
  String endsync=mSmilElement.getAttribute(ENDSYNC_ATTRIBUTE_NAME);
  if ((endsync == null) || (endsync.length() == 0)) {
    setEndSync(ENDSYNC_LAST);
    return ENDSYNC_LAST;
  }
  if (ENDSYNC_FIRST.equals(endsync) || ENDSYNC_LAST.equals(endsync) || ENDSYNC_ALL.equals(endsync) || ENDSYNC_MEDIA.equals(endsync)) {
    return endsync;
  }
  setEndSync(ENDSYNC_LAST);
  return ENDSYNC_LAST;
}
