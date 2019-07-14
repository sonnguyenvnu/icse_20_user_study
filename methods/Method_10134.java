/** 
 * Gets the community bot.
 * @return default commenter
 */
public JSONObject getComBot(){
  final JSONObject ret=getUserByName(UserExt.COM_BOT_NAME);
  ret.remove(UserExt.USER_T_POINT_HEX);
  ret.remove(UserExt.USER_T_POINT_CC);
  return ret;
}
