@ThreadSafe(enableChecks=false) void preAllocateMountContent(boolean shouldPreallocatePerMountSpec){
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("preAllocateMountContent:" + mComponent.getSimpleName());
  }
  if (mMountableOutputs != null && !mMountableOutputs.isEmpty()) {
    for (int i=0, size=mMountableOutputs.size(); i < size; i++) {
      final Component component=mMountableOutputs.get(i).getComponent();
      if (shouldPreallocatePerMountSpec && !component.canPreallocate()) {
        continue;
      }
      if (Component.isMountViewSpec(component)) {
        if (isTracing) {
          ComponentsSystrace.beginSection("preAllocateMountContent:" + component.getSimpleName());
        }
        ComponentsPools.maybePreallocateContent(mContext.getAndroidContext(),component);
        if (isTracing) {
          ComponentsSystrace.endSection();
        }
      }
    }
  }
  if (isTracing) {
    ComponentsSystrace.endSection();
  }
}
