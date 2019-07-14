/** 
 * Parses the command line arguments and executes PMD.
 * @param args command line arguments
 * @return the exit code, where <code>0</code> means successful execution,<code>1</code> means error, <code>4</code> means there have been violations found.
 */
public static int run(String[] args){
  final PMDParameters params=PMDCommandLineInterface.extractParameters(new PMDParameters(),args,"pmd");
  if (params.isBenchmark()) {
    TimeTracker.startGlobalTracking();
  }
  int status=0;
  final PMDConfiguration configuration=params.toConfiguration();
  final Level logLevel=params.isDebug() ? Level.FINER : Level.INFO;
  final ScopedLogHandlersManager logHandlerManager=new ScopedLogHandlersManager(logLevel,new ConsoleHandler());
  final Level oldLogLevel=LOG.getLevel();
  LOG.setLevel(logLevel);
  try {
    int violations=PMD.doPMD(configuration);
    if (violations > 0 && configuration.isFailOnViolation()) {
      status=PMDCommandLineInterface.VIOLATIONS_FOUND;
    }
 else {
      status=0;
    }
  }
 catch (  Exception e) {
    System.out.println(PMDCommandLineInterface.buildUsageText());
    System.out.println();
    System.err.println(e.getMessage());
    status=PMDCommandLineInterface.ERROR_STATUS;
  }
 finally {
    logHandlerManager.close();
    LOG.setLevel(oldLogLevel);
    if (params.isBenchmark()) {
      final TimingReport timingReport=TimeTracker.stopGlobalTracking();
      final TimingReportRenderer renderer=new TextTimingReportRenderer();
      try {
        final Writer writer=new OutputStreamWriter(System.err);
        renderer.render(timingReport,writer);
      }
 catch (      final IOException e) {
        System.err.println(e.getMessage());
      }
    }
  }
  return status;
}
