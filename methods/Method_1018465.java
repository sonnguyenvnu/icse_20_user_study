void newer(){
  startIndex-=VISIBLE_EMAIL_COUNT;
  if (startIndex < 0) {
    startIndex=0;
  }
 else {
    styleRow(selectedRow,false);
    selectedRow=-1;
    update();
  }
}
