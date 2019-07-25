@Override public ActionResult execute(){
  Map<String,InstructorAttributes> instructorsForCourses=new HashMap<>();
  List<CourseAttributes> activeCourses=new ArrayList<>();
  List<CourseAttributes> archivedCourses=new ArrayList<>();
  List<InstructorAttributes> instructorList=logic.getInstructorsForGoogleId(userInfo.id);
  for (  InstructorAttributes instructor : instructorList) {
    instructorsForCourses.put(instructor.courseId,instructor);
  }
  List<CourseAttributes> allCourses=logic.getCoursesForInstructor(instructorList);
  List<CourseAttributes> softDeletedCourses=logic.getSoftDeletedCoursesForInstructors(instructorList);
  List<String> archivedCourseIds=logic.getArchivedCourseIds(allCourses,instructorsForCourses);
  for (  CourseAttributes course : allCourses) {
    if (archivedCourseIds.contains(course.getId())) {
      archivedCourses.add(course);
    }
 else {
      activeCourses.add(course);
    }
  }
  CourseAttributes.sortById(activeCourses);
  CourseAttributes.sortById(archivedCourses);
  CourseAttributes.sortById(softDeletedCourses);
  InstructorCourses output=new InstructorCourses(activeCourses,archivedCourses,softDeletedCourses,instructorList);
  return new JsonResult(output);
}
