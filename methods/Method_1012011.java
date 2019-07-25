void show(Frame owner,Point location,ToolTipData hintInformation){
  myHintInformation=hintInformation;
  location=new Point(location.x + ((myRigthAligned ? -X_OFFSET : X_OFFSET)),location.y + Y_OFFSET);
  myDialog=new ToolTip.MyDialog(owner,location,myRigthAligned,hintInformation);
  myDialog.setVisible(true);
}
