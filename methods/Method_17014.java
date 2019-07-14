@Setup public void setupStriped64(){
  probeOffset=UnsafeAccess.objectFieldOffset(Thread.class,"threadLocalRandomProbe");
}
