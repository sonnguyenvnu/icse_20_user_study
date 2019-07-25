public Instance pipe(Instance carrier){
  AugmentableFeatureVector afv=(AugmentableFeatureVector)carrier.getData();
  double v;
  for (int i=afv.numLocations() - 1; i >= 0; i--) {
    v=afv.valueAtLocation(i);
    if (v >= 1)     afv.setValueAtLocation(i,Math.log(v) + 1);
  }
  return carrier;
}
