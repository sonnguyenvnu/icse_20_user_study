@Override public ActionResult execute(){
  String feedbackResponseId=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_RESPONSE_ID);
  FeedbackResponseAttributes feedbackResponse=logic.getFeedbackResponse(feedbackResponseId);
  FeedbackQuestionAttributes feedbackQuestion=logic.getFeedbackQuestion(feedbackResponse.feedbackQuestionId);
  String giverIdentifier;
  String giverSection;
  Intent intent=Intent.valueOf(getNonNullRequestParamValue(Const.ParamsNames.INTENT));
switch (intent) {
case STUDENT_SUBMISSION:
    StudentAttributes studentAttributes=getStudentOfCourseFromRequest(feedbackQuestion.getCourseId());
  giverIdentifier=feedbackQuestion.getGiverType() == FeedbackParticipantType.TEAMS ? studentAttributes.getTeam() : studentAttributes.getEmail();
giverSection=studentAttributes.getSection();
break;
case INSTRUCTOR_SUBMISSION:
InstructorAttributes instructorAttributes=getInstructorOfCourseFromRequest(feedbackQuestion.getCourseId());
giverIdentifier=instructorAttributes.getEmail();
giverSection=Const.DEFAULT_SECTION;
break;
default :
throw new InvalidHttpParameterException("Unknown intent " + intent);
}
FeedbackResponseUpdateRequest updateRequest=getAndValidateRequestBody(FeedbackResponseUpdateRequest.class);
feedbackResponse.giver=giverIdentifier;
feedbackResponse.giverSection=giverSection;
feedbackResponse.recipient=updateRequest.getRecipientIdentifier();
feedbackResponse.recipientSection=getRecipientSection(feedbackQuestion.getCourseId(),feedbackQuestion.getRecipientType(),updateRequest.getRecipientIdentifier());
feedbackResponse.responseDetails=updateRequest.getResponseDetails();
validResponseOfQuestion(feedbackQuestion,feedbackResponse);
try {
FeedbackResponseAttributes updatedFeedbackResponse=logic.updateFeedbackResponseCascade(FeedbackResponseAttributes.updateOptionsBuilder(feedbackResponse.getId()).withGiver(feedbackResponse.giver).withGiverSection(feedbackResponse.giverSection).withRecipient(feedbackResponse.recipient).withRecipientSection(feedbackResponse.recipientSection).withResponseDetails(feedbackResponse.getResponseDetails()).build());
return new JsonResult(new FeedbackResponseData(updatedFeedbackResponse));
}
 catch (Exception e) {
throw new InvalidHttpRequestBodyException(e.getMessage(),e);
}
}
