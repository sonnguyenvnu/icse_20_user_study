@NonNull public List<ReportSender> getSenderInstances(boolean foreground){
  List<Class<? extends ReportSenderFactory>> factoryClasses=config.reportSenderFactoryClasses();
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"config#reportSenderFactoryClasses : " + factoryClasses);
  final List<ReportSenderFactory> factories;
  if (factoryClasses.isEmpty()) {
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Using PluginLoader to find ReportSender factories");
    final PluginLoader loader=config.pluginLoader();
    factories=loader.loadEnabled(config,ReportSenderFactory.class);
  }
 else {
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Creating reportSenderFactories for reportSenderFactory config");
    factories=new InstanceCreator().create(factoryClasses);
  }
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"reportSenderFactories : " + factories);
  final List<ReportSender> reportSenders=new ArrayList<>();
  for (  ReportSenderFactory factory : factories) {
    final ReportSender sender=factory.create(context,config);
    if (ACRA.DEV_LOGGING)     ACRA.log.d(LOG_TAG,"Adding reportSender : " + sender);
    if (foreground == sender.requiresForeground()) {
      reportSenders.add(sender);
    }
  }
  return reportSenders;
}
