private void stop(){
  meetingManager.leaveMeeting(meetingId,new IXHResultCallback(){
    @Override public void success(    Object data){
      stopAndFinish();
    }
    @Override public void failed(    final String errMsg){
      MLOC.showMsg(VideoMeetingActivity.this,errMsg);
      stopAndFinish();
    }
  }
);
}
