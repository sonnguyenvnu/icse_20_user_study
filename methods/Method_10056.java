/** 
 * Makes the specified notifications have been read.
 * @param notifications the specified notifications
 */
@Transactional public void makeRead(final Collection<JSONObject> notifications){
  for (  final JSONObject notification : notifications) {
    makeRead(notification);
  }
}
