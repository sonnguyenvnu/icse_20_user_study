public void putMessages(final ArrayList<TLRPC.Message> messages,final boolean withTransaction,boolean useQueue,final boolean doNotUpdateDialogDate,final int downloadMask){
  putMessages(messages,withTransaction,useQueue,doNotUpdateDialogDate,downloadMask,false);
}
