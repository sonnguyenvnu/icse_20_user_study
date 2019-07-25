@Override public void validate(){
  assertTrue(questionNumber >= 1,"Invalid question number");
  assertTrue(questionBrief != null,"Question brief cannot be null");
  assertTrue(!questionBrief.isEmpty(),"Question brief cannot be empty");
  assertTrue(questionDetails != null,"Question details cannot be null");
  assertTrue(questionType != null,"Question type cannot be null");
  assertTrue(giverType != null,"Giver type cannot be null");
  assertTrue(recipientType != null,"Recipient type cannot be null");
  assertTrue(numberOfEntitiesToGiveFeedbackToSetting != null,"numberOfEntitiesToGiveFeedbackToSetting cannot be null");
  if (numberOfEntitiesToGiveFeedbackToSetting == NumberOfEntitiesToGiveFeedbackToSetting.CUSTOM) {
    assertTrue(customNumberOfEntitiesToGiveFeedbackTo != null,"customNumberOfEntitiesToGiveFeedbackTo must be set");
  }
  assertTrue(showResponsesTo != null,"showResponsesTo cannot be null");
  assertTrue(showGiverNameTo != null,"showGiverNameTo cannot be null");
  assertTrue(showRecipientNameTo != null,"showRecipientNameTo cannot be null");
}
