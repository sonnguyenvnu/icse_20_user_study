@Override @SuppressWarnings("PMD.PreserveStackTrace") public ActionResult execute(){
  String startTimeString=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_SESSION_STARTTIME);
  long startTime;
  try {
    startTime=Long.parseLong(startTimeString);
    Instant.ofEpochMilli(startTime).minus(Const.FEEDBACK_SESSIONS_SEARCH_WINDOW).toEpochMilli();
  }
 catch (  NumberFormatException|ArithmeticException e) {
    throw new InvalidHttpParameterException("Invalid startTime parameter");
  }
  String endTimeString=getNonNullRequestParamValue(Const.ParamsNames.FEEDBACK_SESSION_ENDTIME);
  long endTime;
  try {
    endTime=Long.parseLong(endTimeString);
    Instant.ofEpochMilli(endTime).plus(Const.FEEDBACK_SESSIONS_SEARCH_WINDOW).toEpochMilli();
  }
 catch (  NumberFormatException|ArithmeticException e) {
    throw new InvalidHttpParameterException("Invalid endTime parameter");
  }
  if (startTime > endTime) {
    throw new InvalidHttpParameterException("The filter range is not valid. End time should be after start time.");
  }
  List<FeedbackSessionAttributes> allOngoingSessions=logic.getAllOngoingSessions(Instant.ofEpochMilli(startTime),Instant.ofEpochMilli(endTime));
  int totalOngoingSessions=allOngoingSessions.size();
  int totalOpenSessions=0;
  int totalClosedSessions=0;
  int totalAwaitingSessions=0;
  Set<String> courseIds=new HashSet<>();
  Map<String,List<FeedbackSessionAttributes>> courseIdToFeedbackSessionsMap=new HashMap<>();
  for (  FeedbackSessionAttributes fs : allOngoingSessions) {
    if (fs.isOpened()) {
      totalOpenSessions++;
    }
    if (fs.isClosed()) {
      totalClosedSessions++;
    }
    if (fs.isWaitingToOpen()) {
      totalAwaitingSessions++;
    }
    String courseId=fs.getCourseId();
    courseIds.add(courseId);
    courseIdToFeedbackSessionsMap.computeIfAbsent(courseId,k -> new ArrayList<>()).add(fs);
  }
  Map<String,List<OngoingSession>> instituteToFeedbackSessionsMap=new HashMap<>();
  for (  String courseId : courseIds) {
    List<InstructorAttributes> instructors=logic.getInstructorsForCourse(courseId);
    AccountAttributes account=getRegisteredInstructorAccountFromInstructors(instructors);
    String institute=account == null ? UNKNOWN_INSTITUTION : account.institute;
    List<OngoingSession> sessions=courseIdToFeedbackSessionsMap.get(courseId).stream().map(session -> new OngoingSession(session,account)).collect(Collectors.toList());
    instituteToFeedbackSessionsMap.computeIfAbsent(institute,k -> new ArrayList<>()).addAll(sessions);
  }
  long totalInstitutes=instituteToFeedbackSessionsMap.keySet().stream().filter(key -> !key.equals(UNKNOWN_INSTITUTION)).count();
  OngoingSessionsData output=new OngoingSessionsData();
  output.setTotalOngoingSessions(totalOngoingSessions);
  output.setTotalOpenSessions(totalOpenSessions);
  output.setTotalClosedSessions(totalClosedSessions);
  output.setTotalAwaitingSessions(totalAwaitingSessions);
  output.setTotalInstitutes(totalInstitutes);
  output.setSessions(instituteToFeedbackSessionsMap);
  return new JsonResult(output);
}
