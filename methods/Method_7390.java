public static boolean isAnyKindOfCallActive(){
  if (VoIPService.getSharedInstance() != null) {
    return VoIPService.getSharedInstance().getCallState() != VoIPService.STATE_WAITING_INCOMING;
  }
  return false;
}
