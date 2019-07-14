/** 
 * Validates comment fields.
 * @param requestJSONObject the specified request object
 * @throws RequestProcessAdviceException if validate failed
 */
private static void validateCommentFields(final JSONObject requestJSONObject) throws RequestProcessAdviceException {
  final BeanManager beanManager=BeanManager.getInstance();
  final LangPropsService langPropsService=beanManager.getReference(LangPropsService.class);
  final OptionQueryService optionQueryService=beanManager.getReference(OptionQueryService.class);
  final JSONObject exception=new JSONObject();
  exception.put(Keys.STATUS_CODE,StatusCodes.ERR);
  final String commentContent=StringUtils.trim(requestJSONObject.optString(Comment.COMMENT_CONTENT));
  if (StringUtils.isBlank(commentContent) || commentContent.length() > Comment.MAX_COMMENT_CONTENT_LENGTH) {
    throw new RequestProcessAdviceException(exception.put(Keys.MSG,langPropsService.get("commentErrorLabel")));
  }
  if (optionQueryService.containReservedWord(commentContent)) {
    throw new RequestProcessAdviceException(exception.put(Keys.MSG,langPropsService.get("contentContainReservedWordLabel")));
  }
}
