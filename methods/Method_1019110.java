private void code(int start,int end){
  int selectedStart=mRxMDEditText.getSelectionStart();
  int selectedEnd=mRxMDEditText.getSelectionEnd();
  int endAdd=0;
  char c=mRxMDEditText.getText().charAt(end >= mRxMDEditText.length() ? mRxMDEditText.length() - 1 : end);
  if (c == '\n') {
    mRxMDEditText.getText().insert(end,"\n```");
    endAdd+=4;
  }
 else {
    mRxMDEditText.getText().insert(end,"\n```\n");
    endAdd+=5;
    selectedStart=selectedStart + 1;
  }
  char c1=mRxMDEditText.getText().charAt(start - 1 < 0 ? 0 : start - 1);
  if (c1 == '\n' || start - 1 < 0) {
    mRxMDEditText.getText().insert(start,"```\n");
    endAdd+=4;
  }
 else {
    mRxMDEditText.getText().insert(start,"\n```\n");
    endAdd+=4;
  }
  mRxMDEditText.setSelection(selectedStart,selectedEnd + endAdd);
}
