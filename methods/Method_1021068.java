public void inject(final Injection type,final String value){
  if (null == injectionValues) {
    injectionValues=new String[Injection.values().length];
  }
  injectionValues[type.ordinal()]=value;
}
