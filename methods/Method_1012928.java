@Override public ActionResult execute(){
  String feedbackResponseId=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_RESPONSE_ID);
  FeedbackResponseAttributes feedbackResponse=logic.getFeedbackResponse(feedbackResponseId);
  logic.deleteFeedbackResponseCascade(feedbackResponse.getId());
  return new JsonResult("Feedback response deleted");
}
