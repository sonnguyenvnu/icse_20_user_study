@SuppressWarnings({"unchecked","rawtypes"}) @Override public BlockSnapshot remove(int index){
  final List<BlockSnapshot> cachedForgeList=getCachedForgeList();
  final BlockSnapshot remove=cachedForgeList.remove(index);
  try {
    final PhaseContext<?> data=PhaseTracker.getInstance().getCurrentContext();
    if (((IPhaseState)data.state).doesBulkBlockCapture(data)) {
      final MultiBlockCaptureSupplier capturedBlockSupplier=data.getCapturedBlockSupplier();
      final SpongeBlockSnapshot snapshot=capturedBlockSupplier.get().get(index);
      if (snapshot != null) {
        capturedBlockSupplier.prune(snapshot);
      }
    }
 else {
      this.wrappedList.remove(index);
    }
  }
 catch (  IndexOutOfBoundsException e) {
  }
  return remove;
}
