@Override public ActionResult execute(){
  String courseId=getNonNullRequestParamValue(Const.ParamsNames.COURSE_ID);
  String feedbackSessionName=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_SESSION_NAME);
  String questionId=getRequestParamValue(Const.ParamsNames.FEEDBACK_QUESTION_ID);
  String selectedSection=getRequestParamValue(Const.ParamsNames.FEEDBACK_RESULTS_GROUPBYSECTION);
  FeedbackSessionResultsBundle bundle;
  Intent intent=Intent.valueOf(getNonNullRequestParamValue(Const.ParamsNames.INTENT));
switch (intent) {
case INSTRUCTOR_RESULT:
    InstructorAttributes instructor=logic.getInstructorForGoogleId(courseId,userInfo.id);
  try {
    if (questionId == null) {
      if (selectedSection == null) {
        bundle=logic.getFeedbackSessionResultsForInstructorWithinRangeFromView(feedbackSessionName,courseId,instructor.email,1,Const.FeedbackSessionResults.QUESTION_SORT_TYPE);
      }
 else {
        bundle=logic.getFeedbackSessionResultsForInstructorInSection(feedbackSessionName,courseId,instructor.email,selectedSection,SectionDetail.EITHER);
      }
    }
 else {
      if (selectedSection == null) {
        bundle=logic.getFeedbackSessionResultsForInstructorFromQuestion(feedbackSessionName,courseId,instructor.email,questionId);
      }
 else {
        bundle=logic.getFeedbackSessionResultsForInstructorFromQuestionInSection(feedbackSessionName,courseId,instructor.email,questionId,selectedSection,SectionDetail.EITHER);
      }
    }
  }
 catch (  EntityDoesNotExistException e) {
    throw new EntityNotFoundException(e);
  }
return new JsonResult(new SessionResultsData(bundle,instructor));
case STUDENT_RESULT:
StudentAttributes student=getStudent(courseId);
try {
bundle=logic.getFeedbackSessionResultsForStudent(feedbackSessionName,courseId,student.email);
}
 catch (EntityDoesNotExistException e) {
throw new EntityNotFoundException(e);
}
if (bundle.isStudentHasSomethingNewToSee(student)) {
}
 else {
}
return new JsonResult(new SessionResultsData(bundle,student));
case INSTRUCTOR_SUBMISSION:
case STUDENT_SUBMISSION:
throw new InvalidHttpParameterException("Invalid intent for this action");
default :
throw new InvalidHttpParameterException("Unknown intent " + intent);
}
}
