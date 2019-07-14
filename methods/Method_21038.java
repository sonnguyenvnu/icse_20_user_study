public boolean isGenericLoginError(){
  return !INVALID_XAUTH_LOGIN.equals(ksrCode()) && !TFA_REQUIRED.equals(ksrCode());
}
