private void setTextLeft(View cell,int index){
  if (cell instanceof HeaderCell) {
    HeaderCell headerCell=(HeaderCell)cell;
    if (index == -1) {
      int left=MAX_QUESTION_LENGTH - (questionString != null ? questionString.length() : 0);
      if (left <= MAX_QUESTION_LENGTH - MAX_QUESTION_LENGTH * 0.7f) {
        headerCell.setText2(String.format("%d",left));
        SimpleTextView textView=headerCell.getTextView2();
        String key=left < 0 ? Theme.key_windowBackgroundWhiteRedText5 : Theme.key_windowBackgroundWhiteGrayText3;
        textView.setTextColor(Theme.getColor(key));
        textView.setTag(key);
      }
 else {
        headerCell.setText2("");
      }
    }
 else {
      headerCell.setText2("");
    }
  }
 else   if (cell instanceof PollEditTextCell) {
    if (index >= 0) {
      PollEditTextCell textCell=(PollEditTextCell)cell;
      int left=MAX_ANSWER_LENGTH - (answers[index] != null ? answers[index].length() : 0);
      if (left <= MAX_ANSWER_LENGTH - MAX_ANSWER_LENGTH * 0.7f) {
        textCell.setText2(String.format("%d",left));
        SimpleTextView textView=textCell.getTextView2();
        String key=left < 0 ? Theme.key_windowBackgroundWhiteRedText5 : Theme.key_windowBackgroundWhiteGrayText3;
        textView.setTextColor(Theme.getColor(key));
        textView.setTag(key);
      }
 else {
        textCell.setText2("");
      }
    }
  }
}
