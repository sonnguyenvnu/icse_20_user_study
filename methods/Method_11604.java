/** 
 * Process specific urls without url discovering.
 * @param urls urls to process
 */
public void test(String... urls){
  initComponent();
  if (urls.length > 0) {
    for (    String url : urls) {
      processRequest(new Request(url));
    }
  }
}
