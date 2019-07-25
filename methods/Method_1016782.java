public Void call() throws Exception {
  for (int ii=start; ii < end; ii++) {
    if (instancesWithConstraints.get(ii)) {
      Instance instance=data.get(ii);
      SumLatticeDefault lattice=new SumLatticeDefault(this.crf,(FeatureVectorSequence)instance.getData(),null,null,true);
      lattices.add(lattice);
    }
 else {
      lattices.add(null);
    }
  }
  return null;
}
