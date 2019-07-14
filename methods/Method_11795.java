@Override public Runner getSuite(RunnerBuilder builder,java.lang.Class<?>[] classes) throws InitializationError {
  Runner suite=super.getSuite(builder,classes);
  return this.classes ? parallelize(suite) : suite;
}
