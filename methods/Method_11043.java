private void initView(final Activity activity){
  View dialogView=null;
switch (mLayoutType) {
case TITLE:
    dialogView=LayoutInflater.from(getContext()).inflate(R.layout.dialog_picker_pictrue,null);
  break;
case NO_TITLE:
dialogView=LayoutInflater.from(getContext()).inflate(R.layout.dialog_camero_show,null);
break;
default :
break;
}
mTvCamera=dialogView.findViewById(R.id.tv_camera);
mTvFile=dialogView.findViewById(R.id.tv_file);
mTvCancel=dialogView.findViewById(R.id.tv_cancel);
mTvCancel.setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View arg0){
cancel();
}
}
);
mTvCamera.setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View arg0){
RxPhotoTool.openCameraImage(activity);
cancel();
}
}
);
mTvFile.setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View arg0){
RxPhotoTool.openLocalImage(activity);
cancel();
}
}
);
setContentView(dialogView);
mLayoutParams.gravity=Gravity.BOTTOM;
}
