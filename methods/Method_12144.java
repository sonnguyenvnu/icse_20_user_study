/** 
 * ??account??????
 */
@Override public void recreateAbstractInfo(){
  List<Account> accounts=this.accountMapper.selectAll();
  for (  Account account : accounts) {
    this.accountMapper.updateByPrimaryKey(account);
  }
}
