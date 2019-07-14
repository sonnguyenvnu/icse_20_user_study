private void checkDoneButton(){
  boolean enabled=true;
  if (TextUtils.isEmpty(getFixedString(questionString)) || questionString.length() > MAX_QUESTION_LENGTH) {
    enabled=false;
  }
 else {
    int count=0;
    for (int a=0; a < answers.length; a++) {
      if (!TextUtils.isEmpty(getFixedString(answers[a]))) {
        if (answers[a].length() > MAX_ANSWER_LENGTH) {
          count=0;
          break;
        }
        count++;
      }
    }
    if (count < 2) {
      enabled=false;
    }
  }
  doneItem.setEnabled(enabled);
  doneItem.setAlpha(enabled ? 1.0f : 0.5f);
}
