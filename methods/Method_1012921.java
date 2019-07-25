@Override protected ActionResult execute() throws EntityDoesNotExistException {
  String courseId=getRequestParamValue(Const.ParamsNames.COURSE_ID);
  String feedbackSessionName=getRequestParamValue(Const.ParamsNames.FEEDBACK_SESSION_NAME);
  boolean showStats=getRequestParamAsOnOffBoolean(Const.ParamsNames.FEEDBACK_RESULTS_SHOWSTATS);
  Assumption.assertPostParamNotNull(Const.ParamsNames.COURSE_ID,courseId);
  Assumption.assertPostParamNotNull(Const.ParamsNames.FEEDBACK_SESSION_NAME,feedbackSessionName);
  statusToAdmin="Show instructor feedback result page<br>" + "Session Name: " + feedbackSessionName + "<br>" + "Course ID: " + courseId;
  InstructorAttributes instructor=logic.getInstructorForGoogleId(courseId,account.googleId);
  FeedbackSessionAttributes session=logic.getFeedbackSession(feedbackSessionName,courseId);
  gateKeeper.verifyAccessible(instructor,session);
  InstructorFeedbackResultsPageData data=new InstructorFeedbackResultsPageData(account,sessionToken);
  String selectedSection=getRequestParamValue(Const.ParamsNames.FEEDBACK_RESULTS_GROUPBYSECTION);
  String sectionDetailValue=getRequestParamValue(Const.ParamsNames.FEEDBACK_RESULTS_GROUPBYSECTIONDETAIL);
  SectionDetail selectedSectionDetail=SectionDetail.NOT_APPLICABLE;
  if (selectedSection == null) {
    selectedSection=ALL_SECTION_OPTION;
  }
 else   if (sectionDetailValue != null && !sectionDetailValue.isEmpty()) {
    Assumption.assertNotNull(SectionDetail.containsSectionDetail(sectionDetailValue));
    selectedSectionDetail=SectionDetail.valueOf(sectionDetailValue);
  }
  boolean isMissingResponsesShown=getRequestParamAsBoolean(Const.ParamsNames.FEEDBACK_RESULTS_INDICATE_MISSING_RESPONSES);
  boolean isLoadingCsvResultsAsHtml=getRequestParamAsBoolean(Const.ParamsNames.CSV_TO_HTML_TABLE_NEEDED);
  if (isLoadingCsvResultsAsHtml) {
    return createAjaxResultForCsvTableLoadedInHtml(courseId,feedbackSessionName,instructor,data,selectedSection,selectedSectionDetail,isMissingResponsesShown,Boolean.valueOf(showStats));
  }
  data.setSessionResultsHtmlTableAsString("");
  data.setAjaxStatus("");
  boolean groupByTeam=getRequestParamAsOnOffBoolean(Const.ParamsNames.FEEDBACK_RESULTS_GROUPBYTEAM);
  String sortType=getRequestParamValue(Const.ParamsNames.FEEDBACK_RESULTS_SORTTYPE);
  String startIndex=getRequestParamValue(Const.ParamsNames.FEEDBACK_RESULTS_MAIN_INDEX);
  if (startIndex != null) {
    data.setStartIndex(Integer.parseInt(startIndex));
  }
  if (sortType == null) {
    showStats=true;
    groupByTeam=true;
    sortType=Const.FeedbackSessionResults.QUESTION_SORT_TYPE;
    isMissingResponsesShown=true;
  }
  String questionId=getRequestParamValue(Const.ParamsNames.FEEDBACK_QUESTION_ID);
  String isTestingAjax=getRequestParamValue(Const.ParamsNames.FEEDBACK_RESULTS_NEED_AJAX);
  if (ALL_SECTION_OPTION.equals(selectedSection) && questionId == null && !Const.FeedbackSessionResults.QUESTION_SORT_TYPE.equals(sortType)) {
    data.setBundle(logic.getFeedbackSessionResultsForInstructorWithinRangeFromView(feedbackSessionName,courseId,instructor.email,DEFAULT_SECTION_QUERY_RANGE,sortType));
  }
 else   if (Const.FeedbackSessionResults.QUESTION_SORT_TYPE.equals(sortType)) {
    data.setBundle(getBundleForQuestionView(isTestingAjax,courseId,feedbackSessionName,instructor,data,selectedSection,selectedSectionDetail,sortType,questionId));
  }
 else   if (Const.FeedbackSessionResults.GQR_SORT_TYPE.equals(sortType) || Const.FeedbackSessionResults.GRQ_SORT_TYPE.equals(sortType)) {
    data.setBundle(logic.getFeedbackSessionResultsForInstructorFromSectionWithinRange(feedbackSessionName,courseId,instructor.email,selectedSection,DEFAULT_SECTION_QUERY_RANGE));
  }
 else   if (Const.FeedbackSessionResults.RQG_SORT_TYPE.equals(sortType) || Const.FeedbackSessionResults.RGQ_SORT_TYPE.equals(sortType)) {
    data.setBundle(logic.getFeedbackSessionResultsForInstructorToSectionWithinRange(feedbackSessionName,courseId,instructor.email,selectedSection,DEFAULT_SECTION_QUERY_RANGE));
  }
  if (data.getBundle() == null) {
    throw new EntityDoesNotExistException("Feedback session " + feedbackSessionName + " does not exist in " + courseId + ".");
  }
  boolean isShowSectionWarningForQuestionView=data.isLargeNumberOfRespondents() && Const.FeedbackSessionResults.QUESTION_SORT_TYPE.equals(sortType);
  boolean isShowSectionWarningForParticipantView=!data.getBundle().isComplete && !Const.FeedbackSessionResults.QUESTION_SORT_TYPE.equals(sortType);
  boolean isMultipleSectionAvailable=data.getBundle().getRosterSectionTeamNameTable().size() > 1;
  if (selectedSection.equals(ALL_SECTION_OPTION) && (isShowSectionWarningForParticipantView || isShowSectionWarningForQuestionView)) {
    if (isMultipleSectionAvailable) {
      statusToUser.add(new StatusMessage(Const.StatusMessages.FEEDBACK_RESULTS_SECTIONVIEWWARNING,StatusMessageColor.WARNING));
    }
 else {
      statusToUser.add(new StatusMessage(Const.StatusMessages.FEEDBACK_RESULTS_QUESTIONVIEWWARNING,StatusMessageColor.WARNING));
    }
    isError=true;
  }
switch (sortType) {
case Const.FeedbackSessionResults.QUESTION_SORT_TYPE:
    data.initForViewByQuestion(instructor,selectedSection,selectedSectionDetail,showStats,groupByTeam,isMissingResponsesShown);
  return createShowPageResult(Const.ViewURIs.INSTRUCTOR_FEEDBACK_RESULTS_BY_QUESTION,data);
case Const.FeedbackSessionResults.RGQ_SORT_TYPE:
data.initForSectionPanelViews(instructor,selectedSection,showStats,groupByTeam,InstructorFeedbackResultsPageViewType.RECIPIENT_GIVER_QUESTION,isMissingResponsesShown);
return createShowPageResult(Const.ViewURIs.INSTRUCTOR_FEEDBACK_RESULTS_BY_RECIPIENT_GIVER_QUESTION,data);
case Const.FeedbackSessionResults.GRQ_SORT_TYPE:
data.initForSectionPanelViews(instructor,selectedSection,showStats,groupByTeam,InstructorFeedbackResultsPageViewType.GIVER_RECIPIENT_QUESTION,isMissingResponsesShown);
return createShowPageResult(Const.ViewURIs.INSTRUCTOR_FEEDBACK_RESULTS_BY_GIVER_RECIPIENT_QUESTION,data);
case Const.FeedbackSessionResults.RQG_SORT_TYPE:
data.initForSectionPanelViews(instructor,selectedSection,showStats,groupByTeam,InstructorFeedbackResultsPageViewType.RECIPIENT_QUESTION_GIVER,isMissingResponsesShown);
return createShowPageResult(Const.ViewURIs.INSTRUCTOR_FEEDBACK_RESULTS_BY_RECIPIENT_QUESTION_GIVER,data);
case Const.FeedbackSessionResults.GQR_SORT_TYPE:
data.initForSectionPanelViews(instructor,selectedSection,showStats,groupByTeam,InstructorFeedbackResultsPageViewType.GIVER_QUESTION_RECIPIENT,isMissingResponsesShown);
return createShowPageResult(Const.ViewURIs.INSTRUCTOR_FEEDBACK_RESULTS_BY_GIVER_QUESTION_RECIPIENT,data);
default :
sortType=Const.FeedbackSessionResults.RGQ_SORT_TYPE;
data.initForSectionPanelViews(instructor,selectedSection,showStats,groupByTeam,InstructorFeedbackResultsPageViewType.RECIPIENT_GIVER_QUESTION,isMissingResponsesShown);
return createShowPageResult(Const.ViewURIs.INSTRUCTOR_FEEDBACK_RESULTS_BY_RECIPIENT_GIVER_QUESTION,data);
}
}
