public void update(int startIndex,int count,int max){
  setVisibility(newerButton,startIndex != 0);
  setVisibility(olderButton,startIndex + MailList.VISIBLE_EMAIL_COUNT < count);
  countLabel.setInnerText("" + (startIndex + 1) + " - " + max + " of " + count);
}
