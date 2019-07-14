@Override public List<Class<? extends UElement>> getApplicableUastTypes(){
  return Collections.<Class<? extends UElement>>singletonList(UClass.class);
}
