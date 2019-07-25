@Override public boolean insert(CommentPO commentPO) throws Exception {
  Assert.notNull(commentPO,"commentPO ????");
  Assert.notNull(commentPO.getTargetId(),"targetId ????");
  Assert.notNull(commentPO.getType(),"type ????");
  commentPO.setId(null);
  commentPO.setUserName("??");
  commentPO.setAvatarUrl(Tools.getAvatar());
  LoginInfoDto user=LoginUserHelper.tryGetUser();
  Optional.ofNullable(user).ifPresent(u -> {
    commentPO.setUserId(u.getId());
    commentPO.setAvatarUrl(u.getAvatarUrl());
    if (commentPO.getType().equals(C_BUG)) {
      commentPO.setUserName(LoginUserHelper.getName(u));
    }
 else {
      commentPO.setUserName(LoginUserHelper.getSecretName(u));
    }
  }
);
  commentPO.setStatus(BugStatus.NEW.getByteValue());
  commentPO.setReply("");
  return super.insert(commentPO);
}
