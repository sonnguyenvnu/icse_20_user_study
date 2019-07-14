private String getPolicyName(){
  return String.format("sketch.HillClimberWindowTinyLfu (%s %.0f%% -> %.0f%%)",strategy.name().toLowerCase(US),100 * (1.0 - initialPercentMain),(100.0 * maxWindow) / maximumSize);
}
