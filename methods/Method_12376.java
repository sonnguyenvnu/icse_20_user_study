@Bean public LoggingNotifier loggerNotifier(InstanceRepository repository){
  return new LoggingNotifier(repository);
}
