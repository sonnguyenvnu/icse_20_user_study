/** 
 * @return a new Request, which contains all of the same tests, but in a new order.
 */
public Request sortRequest(Request request){
  if (request instanceof SortingRequest) {
    return request;
  }
  List<Description> leaves=findLeaves(request);
  Collections.sort(leaves,history.testComparator());
  return constructLeafRequest(leaves);
}
