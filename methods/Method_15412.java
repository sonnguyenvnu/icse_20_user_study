/** 
 * @param request
 * @return
 * @throws Exception
 */
public Object verifyURLList(@NotNull JSONObject request,@NotNull String urlList) throws Exception {
  Object obj=request.get(urlList);
  if (obj instanceof Collection == false) {
    throw new IllegalArgumentException(urlList + " ??? Array ??! ????? [] ?");
  }
  JSONArray array=(JSONArray)obj;
  if (array != null) {
    for (int i=0; i < array.size(); i++) {
      if (StringUtil.isUrl(array.getString(i)) == false) {
        throw new IllegalArgumentException(urlList + " ??? " + array.getString(i) + " ??? URL ??!");
      }
    }
  }
  return null;
}
