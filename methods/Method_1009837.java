/** 
 * Display a selection confirmation dialog.
 * @param participantAdapterItems the selected participants
 */
private void finish(final List<ParticipantAdapterItem> participantAdapterItems){
  final List<String> hiddenUserIds=new ArrayList<>();
  for (  ParticipantAdapterItem item : mHiddenParticipantItems) {
    hiddenUserIds.add(item.mUserId);
  }
  if (null != mRoom) {
    mRoom.getDisplayableMembersAsync(new SimpleApiCallback<List<RoomMember>>(){
      @Override public void onSuccess(      List<RoomMember> members){
        for (        RoomMember member : members) {
          if (TextUtils.equals(member.membership,RoomMember.MEMBERSHIP_JOIN) || TextUtils.equals(member.membership,RoomMember.MEMBERSHIP_INVITE)) {
            hiddenUserIds.add(member.getUserId());
          }
        }
        finishStep2(participantAdapterItems,hiddenUserIds);
      }
    }
);
  }
 else {
    finishStep2(participantAdapterItems,hiddenUserIds);
  }
}
