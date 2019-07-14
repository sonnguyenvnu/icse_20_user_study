@Override public final void reevaluateBuffer(long positionUs){
  for (  SequenceableLoader loader : loaders) {
    loader.reevaluateBuffer(positionUs);
  }
}
