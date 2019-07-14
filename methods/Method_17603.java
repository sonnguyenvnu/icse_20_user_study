private boolean adapt(Node candidate){
  if (adjusted == sampled) {
    return false;
  }
  if (feedback.mightContain(candidate.key)) {
    if (sampled >= (adjusted + gain)) {
      adjusted=sampled;
      if (gain < maxGain) {
        gain++;
      }
    }
    return true;
  }
 else   if (sampled > (adjusted + gain + 1)) {
    adjusted=sampled;
    if (gain > 0) {
      gain--;
    }
  }
  return false;
}
