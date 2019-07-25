private void init(){
  if (createrId.equals(MLOC.userId)) {
    if (meetingId == null) {
      createNewMeeting();
    }
 else {
      joinMeeting();
    }
  }
 else {
    if (meetingId == null) {
      MLOC.showMsg(VideoMeetingActivity.this,"??ID??");
    }
 else {
      joinMeeting();
    }
  }
}
