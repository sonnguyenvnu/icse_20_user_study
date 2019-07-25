@Override public ActionResult execute(){
  String feedbackQuestionId=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_QUESTION_ID);
  Intent intent=Intent.valueOf(getNonNullRequestParamValue(Const.ParamsNames.INTENT));
  FeedbackQuestionAttributes question=logic.getFeedbackQuestion(feedbackQuestionId);
  String giverEmail;
  String giverTeam;
  Map<String,String> recipient;
switch (intent) {
case STUDENT_SUBMISSION:
    StudentAttributes studentAttributes=getStudentOfCourseFromRequest(question.getCourseId());
  giverEmail=studentAttributes.getEmail();
giverTeam=studentAttributes.getTeam();
recipient=logic.getRecipientsOfQuestionForStudent(question,giverEmail,giverTeam);
break;
case INSTRUCTOR_SUBMISSION:
InstructorAttributes instructorAttributes=getInstructorOfCourseFromRequest(question.getCourseId());
giverEmail=instructorAttributes.getEmail();
recipient=logic.getRecipientsOfQuestionForInstructor(question,giverEmail);
break;
default :
throw new InvalidHttpParameterException("Unknown intent " + intent);
}
return new JsonResult(new FeedbackQuestionRecipientsData(recipient));
}
