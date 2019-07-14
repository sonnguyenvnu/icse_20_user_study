public static ModifiableConfiguration buildGraphConfiguration(){
  return new ModifiableConfiguration(ROOT_NS,new CommonsConfiguration(new BaseConfiguration()),BasicConfiguration.Restriction.NONE);
}
