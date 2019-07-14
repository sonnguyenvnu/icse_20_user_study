private String getProgressText(){
  String text;
  if (!isFinish) {
    if (!isStop) {
      text="???" + progress + "%";
    }
 else {
      text="??";
    }
  }
 else {
    text="????";
  }
  return text;
}
