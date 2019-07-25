@Override public void run(){
  final List<Check> checks=checksStore.getChecks(true,true).getValues();
  for (  final Check check : checks) {
    Iterator<Metric> metricByName=Iterables.filter(metrics,new FindByName(check.getName())).iterator();
    if (metricByName.hasNext()) {
      Metric found=metricByName.next();
      CheckRunner runner=checkRunnerFactory.create(check,found.getValue());
      runner.run();
    }
  }
}
