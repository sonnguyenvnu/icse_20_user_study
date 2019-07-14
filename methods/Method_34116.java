public int getExpiresIn(){
  return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L).intValue() : 0;
}
