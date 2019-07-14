/** 
 * ??????
 * @param userName ???
 * @param mobile ???
 * @param password ??
 * @return
 */
@Override @Transactional(rollbackFor=Exception.class) public void registerOffline(String userName,String mobile,String password){
  String userNo=buildNoService.buildUserNo();
  String accountNo=buildNoService.buildAccountNo();
  RpUserInfo rpUserInfo=new RpUserInfo();
  rpUserInfo.setAccountNo(accountNo);
  rpUserInfo.setCreateTime(new Date());
  rpUserInfo.setId(StringUtil.get32UUID());
  rpUserInfo.setStatus(PublicStatusEnum.ACTIVE.name());
  rpUserInfo.setUserName(userName);
  rpUserInfo.setUserNo(userNo);
  rpUserInfo.setMobile(mobile);
  rpUserInfo.setPassword(EncryptUtil.encodeMD5String(password));
  rpUserInfo.setPayPwd(EncryptUtil.encodeMD5String("123456"));
  rpUserInfo.setVersion(0);
  this.saveData(rpUserInfo);
  RpAccount rpAccount=new RpAccount();
  rpAccount.setAccountNo(accountNo);
  rpAccount.setAccountType("0");
  rpAccount.setCreateTime(new Date());
  rpAccount.setEditTime(new Date());
  rpAccount.setId(StringUtil.get32UUID());
  rpAccount.setStatus(PublicStatusEnum.ACTIVE.name());
  rpAccount.setUserNo(userNo);
  rpAccount.setBalance(new BigDecimal("0"));
  rpAccount.setSecurityMoney(new BigDecimal("0"));
  rpAccount.setSettAmount(new BigDecimal("0"));
  rpAccount.setTodayExpend(new BigDecimal("0"));
  rpAccount.setTodayIncome(new BigDecimal("0"));
  rpAccount.setUnbalance(new BigDecimal("0"));
  rpAccount.setTotalExpend(new BigDecimal("0"));
  rpAccount.setTotalIncome(new BigDecimal("0"));
  rpAccountService.saveData(rpAccount);
}
