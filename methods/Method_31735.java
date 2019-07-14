@Override protected Object run(Flyway flyway){
  flyway.baseline();
  return null;
}
