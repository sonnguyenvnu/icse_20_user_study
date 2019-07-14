@Override public void onForwardClick(View v){
  if (hasUrl == false) {
    onDragBottom(true);
  }
 else {
    Message msg=new Message();
    msg.obj=inputedString;
    searchHandler.sendMessage(msg);
  }
}
