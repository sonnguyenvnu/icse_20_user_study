private Request constructLeafRequest(List<Description> leaves){
  final List<Runner> runners=new ArrayList<Runner>();
  for (  Description each : leaves) {
    runners.add(buildRunner(each));
  }
  return new Request(){
    @Override public Runner getRunner(){
      try {
        return new Suite((Class<?>)null,runners){
        }
;
      }
 catch (      InitializationError e) {
        return new ErrorReportingRunner(null,e);
      }
    }
  }
;
}
