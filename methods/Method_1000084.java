private void validate(String privateKey){
  if (StringUtils.startsWithIgnoreCase(privateKey,"0X")) {
    privateKey=privateKey.substring(2);
  }
  if (StringUtils.isNotBlank(privateKey) && privateKey.length() != ChainConstant.PRIVATE_KEY_LENGTH) {
    throw new IllegalArgumentException("Private key(" + privateKey + ") must be " + ChainConstant.PRIVATE_KEY_LENGTH + "-bits hex string.");
  }
}
