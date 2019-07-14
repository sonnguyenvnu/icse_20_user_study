/** 
 * Gets usernames by the specified name prefix.
 * @param namePrefix the specified name prefix
 * @return a list of usernames, for example      <pre>[ { "userName": "", "userAvatarURL": "", }, .... ] </pre>
 */
public List<JSONObject> getUserNamesByPrefix(final String namePrefix){
  final JSONObject nameToSearch=new JSONObject();
  nameToSearch.put(UserExt.USER_T_NAME_LOWER_CASE,namePrefix.toLowerCase());
  int index=Collections.binarySearch(USER_NAMES,nameToSearch,(u1,u2) -> {
    String u1Name=u1.optString(UserExt.USER_T_NAME_LOWER_CASE);
    final String inputName=u2.optString(UserExt.USER_T_NAME_LOWER_CASE);
    if (u1Name.length() < inputName.length()) {
      return u1Name.compareTo(inputName);
    }
    u1Name=u1Name.substring(0,inputName.length());
    return u1Name.compareTo(inputName);
  }
);
  final List<JSONObject> ret=new ArrayList<>();
  if (index < 0) {
    return ret;
  }
  int start=index;
  int end=index;
  while (start > -1 && USER_NAMES.get(start).optString(UserExt.USER_T_NAME_LOWER_CASE).startsWith(namePrefix.toLowerCase())) {
    start--;
  }
  start++;
  if (start < index - 5) {
    end=start + 5;
  }
 else {
    while (end < USER_NAMES.size() && end < index + 5 && USER_NAMES.get(end).optString(UserExt.USER_T_NAME_LOWER_CASE).startsWith(namePrefix.toLowerCase())) {
      end++;
      if (end >= start + 5) {
        break;
      }
    }
  }
  return USER_NAMES.subList(start,end);
}
