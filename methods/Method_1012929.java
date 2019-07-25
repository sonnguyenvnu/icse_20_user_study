@Override public ActionResult execute(){
  String instructorId=getNonNullRequestParamValue(Const.ParamsNames.INSTRUCTOR_ID);
  try {
    logic.downgradeInstructorToStudentCascade(instructorId);
  }
 catch (  EntityDoesNotExistException e) {
    throw new EntityNotFoundException(e);
  }
  return new JsonResult("Instructor account is successfully downgraded to student.",HttpStatus.SC_OK);
}
