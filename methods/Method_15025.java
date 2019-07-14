@Override public void onDialogButtonClick(int requestCode,boolean isPositive){
  if (isPositive && data != null) {
    data.setMyStatus(MomentItem.STATUS_DELETING);
    bindView(data);
    HttpRequest.deleteMoment(moment.getId(),HTTP_DELETE,this);
  }
}
