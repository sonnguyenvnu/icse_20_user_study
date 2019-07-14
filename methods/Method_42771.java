public static void encryptPassword(PmsOperator pmsOperator){
  pmsOperator.setsalt(randomNumberGenerator.nextBytes().toHex());
  String newPassword=new SimpleHash(algorithmName,pmsOperator.getLoginPwd(),ByteSource.Util.bytes(pmsOperator.getCredentialsSalt()),hashIterations).toHex();
  pmsOperator.setLoginPwd(newPassword);
}
