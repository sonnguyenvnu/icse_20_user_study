private void stop(){
  superRoomManager.leaveSuperRoom(new IXHResultCallback(){
    @Override public void success(    Object data){
      stopAndFinish();
    }
    @Override public void failed(    final String errMsg){
      MLOC.showMsg(SuperRoomActivity.this,errMsg);
      stopAndFinish();
    }
  }
);
}
