/** 
 * Gets user link with the specified user name.
 * @param userName the specified user name
 * @return user link
 */
public static String getUserLink(final String userName){
  return "<a href=\"" + Latkes.getServePath() + "/member/" + userName + "\">" + userName + "</a> ";
}
