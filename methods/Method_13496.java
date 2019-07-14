@RequestMapping(value="/account",method=RequestMethod.POST,produces="application/json") public String account(String userId,int money){
  LOGGER.info("Account Service ... xid: " + RootContext.getXID());
  if (random.nextBoolean()) {
    throw new RuntimeException("this is a mock Exception");
  }
  int result=jdbcTemplate.update("update account_tbl set money = money - ? where user_id = ?",new Object[]{money,userId});
  LOGGER.info("Account Service End ... ");
  if (result == 1) {
    return SUCCESS;
  }
  return FAIL;
}
