/** 
 * ???????session
 * @param session
 * @return
 */
@PostMapping("logout") public JSONObject logout(HttpSession session){
  long userId;
  try {
    userId=DemoVerifier.getVisitorId(session);
    Log.d(TAG,"logout  userId = " + userId + "; session.getId() = " + (session == null ? null : session.getId()));
    session.invalidate();
  }
 catch (  Exception e) {
    return DemoParser.newErrorResult(e);
  }
  JSONObject result=DemoParser.newSuccessResult();
  JSONObject user=DemoParser.newSuccessResult();
  user.put(ID,userId);
  user.put(COUNT,1);
  result.put(StringUtil.firstCase(USER_),user);
  return result;
}
