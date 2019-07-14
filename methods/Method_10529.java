private void gpsCheck(){
  if (!RxLocationTool.isGpsEnabled(this)) {
    MaterialDialog.Builder builder=new MaterialDialog.Builder(this);
    MaterialDialog materialDialog=builder.title("GPS???").content("???????????GPS??????").positiveText("???").onPositive(new MaterialDialog.SingleButtonCallback(){
      @Override public void onClick(      @NonNull MaterialDialog dialog,      @NonNull DialogAction which){
        RxLocationTool.openGpsSettings(mContext);
      }
    }
).build();
    materialDialog.setCanceledOnTouchOutside(false);
    materialDialog.setCancelable(false);
    materialDialog.show();
  }
 else {
    getLocation();
  }
}
