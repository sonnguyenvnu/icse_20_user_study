/** 
 * Cancel nested boolean.
 * @param accountNestedDTO the account nested dto
 * @return the boolean
 */
@Transactional(rollbackFor=Exception.class) public boolean cancelNested(AccountNestedDTO accountNestedDTO){
  LOGGER.debug("============ dubbo tcc ????????===============");
  AccountDTO accountDTO=new AccountDTO();
  accountDTO.setUserId(accountNestedDTO.getUserId());
  accountDTO.setAmount(accountNestedDTO.getAmount());
  accountMapper.cancel(accountDTO);
  return Boolean.TRUE;
}
