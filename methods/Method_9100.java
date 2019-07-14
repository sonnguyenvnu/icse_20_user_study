private void accept(){
  delegate.onAcceptTerms(currentAccount);
  TLRPC.TL_help_acceptTermsOfService req=new TLRPC.TL_help_acceptTermsOfService();
  req.id=currentTos.id;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
  }
);
}
