private void updateRows(){
  rowCount=0;
  questionHeaderRow=rowCount++;
  questionRow=rowCount++;
  questionSectionRow=rowCount++;
  answerHeaderRow=rowCount++;
  if (answersCount != 0) {
    answerStartRow=rowCount;
    rowCount+=answersCount;
  }
 else {
    answerStartRow=-1;
  }
  if (answersCount != answers.length) {
    addAnswerRow=rowCount++;
  }
 else {
    addAnswerRow=-1;
  }
  answerSectionRow=rowCount++;
}
