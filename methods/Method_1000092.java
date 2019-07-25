public static AccountStateEntity parse(byte[] data){
  try {
    return new AccountStateEntity().setAccount(Account.parseFrom(data));
  }
 catch (  Exception e) {
    logger.error("parse to AccountStateEntity error! reason: {}",e.getMessage());
  }
  return null;
}
