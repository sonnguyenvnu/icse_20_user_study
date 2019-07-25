@Override public ActionResult execute(){
  String feedbackQuestionId=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_QUESTION_ID);
  Intent intent=Intent.valueOf(getNonNullRequestParamValue(Const.ParamsNames.INTENT));
  FeedbackQuestionAttributes questionAttributes=logic.getFeedbackQuestion(feedbackQuestionId);
  FeedbackResponsesData result;
switch (intent) {
case STUDENT_SUBMISSION:
    StudentAttributes studentAttributes=getStudentOfCourseFromRequest(questionAttributes.getCourseId());
  result=new FeedbackResponsesData(logic.getFeedbackResponsesFromStudentOrTeamForQuestion(questionAttributes,studentAttributes));
break;
case INSTRUCTOR_SUBMISSION:
InstructorAttributes instructorAttributes=getInstructorOfCourseFromRequest(questionAttributes.getCourseId());
result=new FeedbackResponsesData(logic.getFeedbackResponsesFromInstructorForQuestion(questionAttributes,instructorAttributes));
break;
default :
throw new InvalidHttpParameterException("Unknown intent " + intent);
}
return new JsonResult(result);
}
