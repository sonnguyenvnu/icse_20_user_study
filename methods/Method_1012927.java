@Override public ActionResult execute(){
  String idOfCourseToDelete=getNonNullRequestParamValue(Const.ParamsNames.COURSE_ID);
  logic.deleteCourseCascade(idOfCourseToDelete);
  return new JsonResult(new MessageOutput("OK"));
}
