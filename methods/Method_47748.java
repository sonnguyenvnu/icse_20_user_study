@Override public void onCommandExecuted(@NonNull Command command,@Nullable Long refreshKey){
  if (command.isRemote())   return;
  JSONObject msg=toJSONObject(command.toJson());
  Long now=new Date().getTime();
  Event e=new Event(command.getId(),now,msg.toString());
  repository.save(e);
  Log.i("SyncManager","Adding to outbox: " + msg.toString());
  pendingEmit.add(e);
  if (readyToEmit)   emitPending();
}
