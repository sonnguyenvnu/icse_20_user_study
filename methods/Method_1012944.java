@Override public ActionResult execute(){
  String idOfCourseToRestore=getNonNullRequestParamValue(Const.ParamsNames.COURSE_ID);
  String statusMessage;
  try {
    logic.restoreCourseFromRecycleBin(idOfCourseToRestore);
    statusMessage="The course " + idOfCourseToRestore + " has been restored.";
  }
 catch (  EntityDoesNotExistException e) {
    return new JsonResult(e.getMessage(),HttpStatus.SC_NOT_FOUND);
  }
  return new JsonResult(statusMessage);
}
