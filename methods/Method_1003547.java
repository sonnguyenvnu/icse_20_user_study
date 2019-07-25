private Annotation singleton(){
  return new Annotation(getTypeFactory().getType("javax.inject.Singleton"));
}
