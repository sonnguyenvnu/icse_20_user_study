@Override public ActionResult execute(){
  String feedbackQuestionID=getRequestParamValue(Const.ParamsNames.FEEDBACK_QUESTION_ID);
  if (feedbackQuestionID != null) {
    if (logic.getFeedbackQuestion(feedbackQuestionID) == null) {
      return new JsonResult("No feedback question with id: " + feedbackQuestionID,HttpStatus.SC_NOT_FOUND);
    }
    boolean hasResponses=logic.areThereResponsesForQuestion(feedbackQuestionID);
    return new JsonResult(new HasResponsesData(hasResponses));
  }
  String courseId=getNonNullRequestParamValue(Const.ParamsNames.COURSE_ID);
  if (logic.getCourse(courseId) == null) {
    return new JsonResult("No course with id: " + courseId,HttpStatus.SC_NOT_FOUND);
  }
  boolean hasResponses=logic.hasResponsesForCourse(courseId);
  return new JsonResult(new HasResponsesData(hasResponses));
}
