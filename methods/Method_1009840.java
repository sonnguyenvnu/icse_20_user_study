/** 
 * Refresh the display.
 * @param theFirstEntry  the first entry in the result.
 * @param searchListener the search result listener
 */
private void refresh(final ParticipantAdapterItem theFirstEntry,final OnParticipantsSearchListener searchListener){
  if (!mSession.isAlive()) {
    Log.e(LOG_TAG,"refresh : the session is not anymore active");
    return;
  }
  if (mLocalContactsSnapshotSession != ContactsManager.getInstance().getLocalContactsSnapshotSession()) {
synchronized (LOG_TAG) {
      mUnusedParticipants=null;
      mContactsParticipants=null;
      mUsedMemberUserIds=null;
      mDisplayNamesList=null;
    }
    mLocalContactsSnapshotSession=ContactsManager.getInstance().getLocalContactsSnapshotSession();
  }
  if (!TextUtils.isEmpty(mPattern)) {
    fillUsedMembersList(new SimpleApiCallback<Void>(){
      @Override public void onSuccess(      Void info){
        final String fPattern=mPattern;
        mSession.searchUsers(mPattern,MAX_USERS_SEARCH_COUNT,mUsedMemberUserIds,new ApiCallback<SearchUsersResponse>(){
          @Override public void onSuccess(          SearchUsersResponse searchUsersResponse){
            if (TextUtils.equals(fPattern,mPattern)) {
              List<ParticipantAdapterItem> participantItemList=new ArrayList<>();
              if (null != searchUsersResponse.results) {
                for (                User user : searchUsersResponse.results) {
                  participantItemList.add(new ParticipantAdapterItem(user));
                }
              }
              mIsOfflineContactsSearch=false;
              mKnownContactsLimited=(null != searchUsersResponse.limited) ? searchUsersResponse.limited : false;
              searchAccountKnownContacts(theFirstEntry,participantItemList,false,searchListener);
            }
          }
          private void onError(){
            if (TextUtils.equals(fPattern,mPattern)) {
              mIsOfflineContactsSearch=true;
              searchAccountKnownContacts(theFirstEntry,new ArrayList<ParticipantAdapterItem>(),true,searchListener);
            }
          }
          @Override public void onNetworkError(          Exception e){
            onError();
          }
          @Override public void onMatrixError(          MatrixError e){
            onError();
          }
          @Override public void onUnexpectedError(          Exception e){
            onError();
          }
        }
);
      }
    }
);
  }
 else {
    searchAccountKnownContacts(theFirstEntry,new ArrayList<ParticipantAdapterItem>(),true,searchListener);
  }
}
