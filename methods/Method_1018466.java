void older(){
  startIndex+=VISIBLE_EMAIL_COUNT;
  if (startIndex >= MailItems.getMailItemCount()) {
    startIndex-=VISIBLE_EMAIL_COUNT;
  }
 else {
    styleRow(selectedRow,false);
    selectedRow=-1;
    update();
  }
}
