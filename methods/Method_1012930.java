@Override public ActionResult execute(){
  String courseId=getNonNullRequestParamValue(Const.ParamsNames.COURSE_ID);
  try {
    List<String> sectionNames=logic.getSectionNamesForCourse(courseId);
    return new JsonResult(new CourseSectionNamesData(sectionNames));
  }
 catch (  EntityDoesNotExistException e) {
    throw new EntityNotFoundException(e);
  }
}
