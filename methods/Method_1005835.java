/** 
 * ??
 * @param bugLog
 * @return
 */
@Override public boolean insert(BugLogPO bugLog) throws Exception {
  Assert.notNull(bugLog);
  Assert.notNull(bugLog.getBugId());
  Assert.notNull(bugLog.getProjectId());
  if (bugLog == null) {
    return false;
  }
  LoginInfoDto user=LoginUserHelper.getUser();
  bugLog.setOperator(user.getId());
  bugLog.setOperatorStr(MyString.isEmpty(user.getTrueName()) ? user.getUserName() : user.getTrueName());
  bugLog.setStatus(Byte.parseByte("1"));
  return super.insert(bugLog);
}
