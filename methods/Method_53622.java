public void generateXML(CycleModel cycleModel){
  String str="<KeyFrameSet>\n\n";
  int offset=str.length(), tmpOffset=str.length();
  for (  Cycle cycle : myCycles) {
    tmpOffset=str.length();
    str+=cycle.myModel.getKeyFrames();
    if (cycleModel == cycle.myModel) {
      offset=tmpOffset;
    }
  }
  str+="</KeyFrameSet>\n";
  myXmlOutput.setText(str);
  int start=cycleModel.start_caret + offset;
  int end=cycleModel.end_caret + offset;
  myXmlOutput.setCaretPosition(start);
  ;
  SwingUtilities.invokeLater(() -> {
    myXmlOutput.setCaretPosition(end);
  }
);
  if (timer != null) {
    timer.stop();
  }
  timer=new Timer(1000,(e) -> {
    myXmlOutput.requestFocus();
    myXmlOutput.select(start,end);
    timer=null;
  }
);
  timer.setRepeats(false);
  timer.start();
}
