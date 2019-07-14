/** 
 * @param request a request to run
 * @return a list of method-level tests to run, sorted in the orderspecified in the class comment.
 */
public List<Description> sortedLeavesForTest(Request request){
  return findLeaves(sortRequest(request));
}
