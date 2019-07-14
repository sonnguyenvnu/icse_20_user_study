public static void onUpdateConfig(long address,final int currentAccount){
  try {
    NativeByteBuffer buff=NativeByteBuffer.wrap(address);
    buff.reused=true;
    final TLRPC.TL_config message=TLRPC.TL_config.TLdeserialize(buff,buff.readInt32(true),true);
    if (message != null) {
      Utilities.stageQueue.postRunnable(() -> MessagesController.getInstance(currentAccount).updateConfig(message));
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
