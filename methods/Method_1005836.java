/** 
 * ??
 * @param bug
 * @return
 */
@Override public boolean insert(BugPO bug) throws Exception {
  Assert.notNull(bug);
  Assert.notNull(bug.getProjectId());
  if (bug == null) {
    return false;
  }
  if (bug.getContent() == null) {
    bug.setContent("");
  }
  LoginInfoDto user=LoginUserHelper.getUser();
  bug.setCreator(user.getId());
  bug.setCreatorStr(MyString.isEmpty(user.getTrueName()) ? user.getUserName() : user.getTrueName());
  bug.setStatus(BugStatus.NEW.getByteValue());
  return super.insert(bug);
}
