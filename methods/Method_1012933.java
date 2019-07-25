@Override public ActionResult execute(){
  String courseId=getRequestParamValue(Const.ParamsNames.COURSE_ID);
  String entityType=getNonNullRequestParamValue(Const.ParamsNames.ENTITY_TYPE);
  List<FeedbackSessionAttributes> feedbackSessionAttributes;
  if (courseId == null) {
    if (entityType.equals(Const.EntityType.STUDENT)) {
      List<StudentAttributes> students=logic.getStudentsForGoogleId(userInfo.getId());
      feedbackSessionAttributes=new ArrayList<>();
      for (      StudentAttributes student : students) {
        feedbackSessionAttributes.addAll(logic.getFeedbackSessionsForCourse(student.getCourse()));
      }
    }
 else {
      boolean isInRecycleBin=getBooleanRequestParamValue(Const.ParamsNames.IS_IN_RECYCLE_BIN);
      List<InstructorAttributes> instructors=logic.getInstructorsForGoogleId(userInfo.getId(),true);
      if (isInRecycleBin) {
        feedbackSessionAttributes=logic.getSoftDeletedFeedbackSessionsListForInstructors(instructors);
      }
 else {
        feedbackSessionAttributes=logic.getFeedbackSessionsListForInstructor(instructors);
      }
    }
  }
 else {
    feedbackSessionAttributes=logic.getFeedbackSessionsForCourse(courseId);
  }
  if (entityType.equals(Const.EntityType.STUDENT)) {
    feedbackSessionAttributes=feedbackSessionAttributes.stream().filter(FeedbackSessionAttributes::isVisible).collect(Collectors.toList());
  }
  FeedbackSessionsData responseData=new FeedbackSessionsData(feedbackSessionAttributes);
  if (entityType.equals(Const.EntityType.STUDENT)) {
    responseData.getFeedbackSessions().forEach(FeedbackSessionData::hideInformationForStudent);
  }
  return new JsonResult(responseData);
}
