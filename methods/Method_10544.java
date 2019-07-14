private void initCameraEvent(final byte[] data){
  String fileDir=RxFileTool.getRootPath().getAbsolutePath() + File.separator + "RoadExcel" + File.separator + "picture";
  String fileName=RxTimeTool.getCurrentDateTime("yyyyMMddHHmmss") + "_" + new Random().nextInt(1000) + ".jpg";
  RxCameraTool.initCameraEvent(mContext,mCameraView,data,fileDir,fileName,mLongitude,mLatitude,false,new OnRxCamera(){
    @Override public void onBefore(){
      mTvState.setText("????,????\n");
    }
    @Override public void onSuccessCompress(    File file){
      mTvState.setText(String.format("%s??????\n",mTvState.getText()));
      photo=file;
      mIvPic.setImageURI(RxFileTool.getImageContentUri(mContext,photo));
    }
    @Override public void onSuccessExif(    File filePhoto){
      mTvState.setText(String.format("%s????????????\n",mTvState.getText()));
      photo=filePhoto;
      mIvPic.setImageURI(RxFileTool.getImageContentUri(mContext,photo));
    }
  }
);
}
