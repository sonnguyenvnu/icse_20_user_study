public boolean similar(HiddenMarkovModel model){
  if (!similar(start_probability,model.start_probability))   return false;
  for (int i=0; i < transition_probability.length; i++) {
    if (!similar(transition_probability[i],model.transition_probability[i]))     return false;
    if (!similar(emission_probability[i],model.emission_probability[i]))     return false;
  }
  return true;
}
