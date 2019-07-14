@Override protected Object run(Flyway flyway){
  flyway.clean();
  return null;
}
