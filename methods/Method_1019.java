@Nullable private static FrameScheduler createSchedulerForBackendAndDelayMethod(@Nullable AnimationBackend animationBackend){
  if (animationBackend == null) {
    return null;
  }
  return new DropFramesFrameScheduler(animationBackend);
}
