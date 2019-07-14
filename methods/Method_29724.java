public void start(){
  mProcessingThread=new Thread(this);
  setActive(true);
  mProcessingThread.start();
  mCamera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback(){
    @Override public void onPreviewFrame(    byte[] bytes,    Camera camera){
      setNextFrame(bytes,camera);
    }
  }
);
  mCamera.addCallbackBuffer(createPreviewBuffer(mPreviewSize));
  mCamera.addCallbackBuffer(createPreviewBuffer(mPreviewSize));
  mCamera.addCallbackBuffer(createPreviewBuffer(mPreviewSize));
  mCamera.addCallbackBuffer(createPreviewBuffer(mPreviewSize));
}
