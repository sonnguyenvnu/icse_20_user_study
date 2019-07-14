@Override public void registerDebugComponent(DebugComponent debugComponent){
  if (mDebugComponents == null) {
    mDebugComponents=new HashSet<>();
  }
  mDebugComponents.add(debugComponent);
}
