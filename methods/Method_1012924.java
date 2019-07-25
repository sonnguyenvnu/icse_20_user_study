public void init(FeedbackResponseCommentSearchResultBundle frcSearchResultBundle,StudentSearchResultBundle studentSearchResultBundle,String searchKey,boolean isSearchFeedbackSessionData,boolean isSearchForStudents){
  this.searchKey=searchKey;
  this.isSearchFeedbackSessionData=isSearchFeedbackSessionData;
  this.isSearchForStudents=isSearchForStudents;
  this.isFeedbackSessionDataEmpty=frcSearchResultBundle.numberOfResults == 0;
  this.isStudentsEmpty=studentSearchResultBundle.numberOfResults == 0;
  setSearchFeedbackSessionDataTables(frcSearchResultBundle);
  setSearchStudentsTables(studentSearchResultBundle);
}
