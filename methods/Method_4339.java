@Override public void onTracksSelected(Renderer[] renderers,TrackGroupArray trackGroups,TrackSelectionArray trackSelections){
  targetBufferSize=targetBufferBytesOverwrite == C.LENGTH_UNSET ? calculateTargetBufferSize(renderers,trackSelections) : targetBufferBytesOverwrite;
  allocator.setTargetBufferSize(targetBufferSize);
}
