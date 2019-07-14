private ModifiableConfiguration buildJobConfiguration(){
  return new ModifiableConfiguration(JOB_NS,new CommonsConfiguration(new BaseConfiguration()),BasicConfiguration.Restriction.NONE);
}
