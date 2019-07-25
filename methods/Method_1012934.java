@Override public ActionResult execute(){
  String courseId=getNonNullRequestParamValue(Const.ParamsNames.COURSE_ID);
  String feedbackSessionName=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_SESSION_NAME);
  FeedbackSessionResponseStatus fsResponseStatus;
  try {
    fsResponseStatus=logic.getFeedbackSessionResponseStatus(feedbackSessionName,courseId);
  }
 catch (  EntityDoesNotExistException e) {
    return new JsonResult("No session with given feedback session name and course id.",HttpStatus.SC_NOT_FOUND);
  }
  return new JsonResult(new FeedbackSessionStudentsResponseData(fsResponseStatus));
}
