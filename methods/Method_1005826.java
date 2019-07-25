@RequestMapping("/preAdd.do") @ResponseBody public JsonResult detail(@ModelAttribute CommentDTO commentDTO) throws Exception {
  Assert.notNull(commentDTO.getType(),"type ????");
  SettingDto anonymousComment=settingCache.get(S_ANONYMOUS_COMMENT);
  commentDTO.setNeedImgCode(false);
  if (commentDTO.getType().equals(C_BUG)) {
    commentDTO.setNeedImgCode(false);
  }
  if (anonymousComment != null && !C_TRUE.equals(anonymousComment.getValue())) {
    commentDTO.setNeedImgCode(true);
  }
  if (settingCache.get(S_COMMENTCODE).getValue().equals("true")) {
    commentDTO.setNeedImgCode(true);
  }
  LoginInfoDto user=LoginUserHelper.tryGetUser();
  if (user != null) {
    commentDTO.setNeedImgCode(false);
  }
  return new JsonResult(1,commentDTO);
}
