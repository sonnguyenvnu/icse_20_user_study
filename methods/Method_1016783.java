public Void call() throws Exception {
  for (int ii=start; ii < end; ii++) {
    if (instancesWithConstraints.get(ii)) {
      SumLattice lattice=lattices.get(ii);
      FeatureVectorSequence fvs=(FeatureVectorSequence)data.get(ii).getData();
      new GELattice(fvs,lattice.getGammas(),lattice.getXis(),crf,reverseTrans,reverseTransIndices,gradient,this.constraints,false);
    }
  }
  return null;
}
