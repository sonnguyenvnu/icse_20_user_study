/** 
 * Searches for comments, using a list of instructors as a constraint.
 */
public FeedbackResponseCommentSearchResultBundle search(String queryString,List<InstructorAttributes> instructors){
  if (queryString.trim().isEmpty()) {
    return new FeedbackResponseCommentSearchResultBundle();
  }
  Results<ScoredDocument> results=searchDocuments(Const.SearchIndex.FEEDBACK_RESPONSE_COMMENT,new FeedbackResponseCommentSearchQuery(instructors,queryString));
  return FeedbackResponseCommentSearchDocument.fromResults(results,instructors);
}
