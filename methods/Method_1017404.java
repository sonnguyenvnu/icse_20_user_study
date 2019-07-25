/** 
 * removes all mocks stored in this  {@code RESTMockServer} as well as all history requests
 */
public static void reset(){
  dispatcher.removeAllMatchableCalls();
  dispatcher.clearHistoricalRequests();
}
