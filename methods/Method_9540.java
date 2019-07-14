private void addNewField(){
  answersCount++;
  if (answersCount == answers.length) {
    listAdapter.notifyItemRemoved(addAnswerRow);
  }
  listAdapter.notifyItemInserted(addAnswerRow);
  updateRows();
  requestFieldFocusAtPosition=answerStartRow + answersCount - 1;
  listAdapter.notifyItemChanged(answerSectionRow);
}
