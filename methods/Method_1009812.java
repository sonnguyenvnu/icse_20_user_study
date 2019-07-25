private void stop(){
  liveManager.leaveLive(new IXHResultCallback(){
    @Override public void success(    Object data){
      stopAndFinish();
    }
    @Override public void failed(    final String errMsg){
      MLOC.showMsg(VideoLiveActivity.this,errMsg);
      stopAndFinish();
    }
  }
);
}
