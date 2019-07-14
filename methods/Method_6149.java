public static boolean isBannedForever(TLRPC.TL_chatBannedRights rights){
  return rights == null || Math.abs(rights.until_date - System.currentTimeMillis() / 1000) > 5 * 365 * 24 * 60 * 60;
}
