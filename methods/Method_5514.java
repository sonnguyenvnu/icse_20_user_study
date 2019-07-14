private void handleMidrowCtrl(byte cc2){
  currentCueBuilder.append(' ');
  boolean underline=(cc2 & 0x01) == 0x01;
  int style=(cc2 >> 1) & 0x07;
  currentCueBuilder.setStyle(style,underline);
}
