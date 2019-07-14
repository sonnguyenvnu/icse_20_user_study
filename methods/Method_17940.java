static void onContextCreated(Context context){
synchronized (sMountContentLock) {
    if (sMountContentPoolsByContext.containsKey(context)) {
      throw new IllegalStateException("The MountContentPools has a reference to an activity that has just been created");
    }
  }
}
