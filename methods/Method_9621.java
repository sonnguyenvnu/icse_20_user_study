private void loadSessions(boolean silent){
  if (loading) {
    return;
  }
  if (!silent) {
    loading=true;
  }
  if (currentType == 0) {
    TLRPC.TL_account_getAuthorizations req=new TLRPC.TL_account_getAuthorizations();
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      loading=false;
      if (error == null) {
        sessions.clear();
        passwordSessions.clear();
        TLRPC.TL_account_authorizations res=(TLRPC.TL_account_authorizations)response;
        for (int a=0, N=res.authorizations.size(); a < N; a++) {
          TLRPC.TL_authorization authorization=res.authorizations.get(a);
          if ((authorization.flags & 1) != 0) {
            currentSession=authorization;
          }
 else           if (authorization.password_pending) {
            passwordSessions.add(authorization);
          }
 else {
            sessions.add(authorization);
          }
        }
        updateRows();
      }
      if (listAdapter != null) {
        listAdapter.notifyDataSetChanged();
      }
    }
));
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
 else {
    TLRPC.TL_account_getWebAuthorizations req=new TLRPC.TL_account_getWebAuthorizations();
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      loading=false;
      if (error == null) {
        sessions.clear();
        TLRPC.TL_account_webAuthorizations res=(TLRPC.TL_account_webAuthorizations)response;
        MessagesController.getInstance(currentAccount).putUsers(res.users,false);
        sessions.addAll(res.authorizations);
        updateRows();
      }
      if (listAdapter != null) {
        listAdapter.notifyDataSetChanged();
      }
    }
));
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
}
