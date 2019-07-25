@Override public void handle(GameEvent event){
  DebugCommandEvent e=(DebugCommandEvent)event;
  if (e.getName().equals("comPortMsg")) {
    ObjectId objectId=e.getObjectId("objectId");
    GameObject object=GameServer.INSTANCE.getGameUniverse().getObject(objectId);
    if (object != null) {
      if (object instanceof MessageReceiver) {
        e.reply("Result: " + ((MessageReceiver)object).sendMessage(e.getString("message").toCharArray()));
      }
 else {
        e.reply("Object " + objectId + " not MessageReceiver");
      }
    }
 else {
      e.reply("Object " + objectId + " not found");
    }
  }
}
