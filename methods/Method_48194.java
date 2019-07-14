protected void verifyOption(ConfigOption option){
  Preconditions.checkNotNull(option);
  super.verifyElement(option);
  if (restriction == Restriction.GLOBAL)   Preconditions.checkArgument(option.isGlobal(),"Can only accept global options: %s",option);
 else   if (restriction == Restriction.LOCAL)   Preconditions.checkArgument(option.isLocal(),"Can only accept local options: %s",option);
}
