public Instance pipe(Instance carrier){
  if (carrier.getTarget() != null) {
    if (carrier.getTarget() instanceof Label)     throw new IllegalArgumentException("Already a label.");
    LabelAlphabet ldict=(LabelAlphabet)getTargetAlphabet();
    carrier.setTarget(ldict.lookupLabel(carrier.getTarget()));
  }
  return carrier;
}
