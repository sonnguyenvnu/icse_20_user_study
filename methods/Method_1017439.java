@Override public String uniqueid(){
  return String.format("%s_%s",perTicketManager.getThirdId(),perTicketManager.getAuthAppId());
}
