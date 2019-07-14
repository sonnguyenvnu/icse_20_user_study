@Override public void onHttpResponse(int requestCode,String result,Exception e){
  if (data == null) {
    Log.e(TAG,"onHttpResponse  data == null  >> return;");
    return;
  }
  JSONResponse response=new JSONResponse(result).getJSONResponse(Moment.class.getSimpleName());
  boolean isSucceed=JSONResponse.isSuccess(response);
  boolean refresh=false;
switch (requestCode) {
case HTTP_PRAISE:
case HTTP_CANCEL_PRAISE:
    if (isSucceed) {
      data.setIsPraised(requestCode == HTTP_PRAISE);
      refresh=true;
    }
 else {
      showShortToast((requestCode == HTTP_PRAISE ? "??" : "????") + "???????????");
    }
  break;
case HTTP_DELETE:
showShortToast(isSucceed ? R.string.delete_succeed : R.string.delete_failed);
if (isSucceed) {
bindView(null);
status=MomentItem.STATUS_DELETED;
CacheManager.getInstance().remove(MomentItem.class,"" + momentId);
}
 else {
status=data.getMyStatus();
bindView(data);
}
break;
}
if (refresh) {
if (onDataChangedListener != null) {
onDataChangedListener.onDataChanged();
}
 else {
bindView(data);
}
}
}
