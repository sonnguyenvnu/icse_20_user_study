/** 
 * Sends a HTML mail for toMailList.
 * @param fromName
 * @param subject
 * @param toMailList
 * @param html
 */
public void sendHTML(final String fromName,final String subject,final List<String> toMailList,String html){
  if (null != toMailList && toMailList.size() > 0) {
    sendHTML(fromName,subject,toMailList.toArray(new String[toMailList.size()]),html);
  }
}
