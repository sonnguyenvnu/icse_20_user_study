/** 
 * Checks whether is bot.
 * @return {@code true} if it is bot, returns {@code false} otherwise
 */
public static boolean isBot(){
  final JSONObject data=THREAD_LOCAL_DATA.get();
  if (null == data) {
    return false;
  }
  return data.optBoolean(Keys.HttpRequest.IS_SEARCH_ENGINE_BOT);
}
