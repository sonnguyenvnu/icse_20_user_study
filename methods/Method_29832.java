private DeleteBroadcastCommentWriter findWriter(long broadcastId,long commentId){
  for (  DeleteBroadcastCommentWriter writer : getWriters()) {
    if (writer.getBroadcastId() == broadcastId && writer.getCommentId() == commentId) {
      return writer;
    }
  }
  return null;
}
