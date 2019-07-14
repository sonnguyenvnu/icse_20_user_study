private void fetchBeamFromPool(int beamWidth,ArrayList<Configuration> beam,TreeSet<BeamElement> beamPreserver) throws InterruptedException, ExecutionException {
  for (int b=0; b < beam.size(); b++) {
    for (    BeamElement element : pool.take().get()) {
      beamPreserver.add(element);
      if (beamPreserver.size() > beamWidth)       beamPreserver.pollFirst();
    }
  }
}
