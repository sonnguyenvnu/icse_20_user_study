private FollowUserWriter findWriter(String userIdOrUid){
  for (  FollowUserWriter writer : getWriters()) {
    if (writer.hasUserIdOrUid(userIdOrUid)) {
      return writer;
    }
  }
  return null;
}
