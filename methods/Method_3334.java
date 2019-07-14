public void unLog(){
  for (int i=0; i < start_probability.length; i++) {
    start_probability[i]=(float)Math.exp(start_probability[i]);
  }
  for (int i=0; i < emission_probability.length; i++) {
    for (int j=0; j < emission_probability[i].length; j++) {
      emission_probability[i][j]=(float)Math.exp(emission_probability[i][j]);
    }
  }
  for (int i=0; i < transition_probability.length; i++) {
    for (int j=0; j < transition_probability[i].length; j++) {
      transition_probability[i][j]=(float)Math.exp(transition_probability[i][j]);
    }
  }
}
