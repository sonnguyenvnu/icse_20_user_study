@TargetApi(15) @Override void setPreviewParameters(final int width,final int height,final int format){
  super.setPreviewParameters(width,height,format);
  mContainer.setPreviewSize(new Size(width,height));
  mContainer.post(new Runnable(){
    @Override public void run(){
      getSurfaceHolder().setFixedSize(getPreviewWidth(),getPreviewHeight());
    }
  }
);
}
