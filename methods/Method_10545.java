@OnClick({R.id.btn_take_camera,R.id.iv_pic}) public void onViewClicked(View view){
switch (view.getId()) {
case R.id.btn_take_camera:
    if (RxTool.isFastClick(1000)) {
      RxToast.normal("???????????");
      return;
    }
 else {
      RxCameraTool.takePic(mContext,mCameraView);
    }
  break;
case R.id.iv_pic:
if (photo == null) {
  RxToast.normal("????");
}
 else {
  RxDialogScaleView rxDialogScaleView=new RxDialogScaleView(mContext);
  rxDialogScaleView.setImage(photo.getAbsolutePath(),false);
  rxDialogScaleView.show();
}
break;
default :
break;
}
}
