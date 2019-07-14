private Statement statement(final Statement base){
  return new Statement(){
    @Override public void evaluate() throws Throwable {
      before();
      List<Throwable> errors=new ArrayList<Throwable>();
      try {
        base.evaluate();
      }
 catch (      Throwable t) {
        errors.add(t);
      }
 finally {
        try {
          after();
        }
 catch (        Throwable t) {
          errors.add(t);
        }
      }
      MultipleFailureException.assertEmpty(errors);
    }
  }
;
}
