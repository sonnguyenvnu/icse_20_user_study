public String translate(int errorCode){
  Integer resId=mErrorMessages.get(errorCode);
  String errorId=mErrorIds.get(errorCode);
  if (errorId == null) {
    errorId=String.valueOf(errorCode);
  }
  if (resId != null) {
    return mContext.getResources().getString(resId,errorId);
  }
  return mContext.getResources().getString(R.string.error_unknown,errorId);
}
