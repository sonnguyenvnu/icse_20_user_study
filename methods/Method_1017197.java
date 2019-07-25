static void interactive(ShellParameters params,CoreInterface core) throws Exception {
  log.info("Setting up interactive shell...");
  Exception e=null;
  try {
    runInteractiveShell(core);
  }
 catch (  final Exception inner) {
    e=inner;
  }
  log.info("Closing core bridge...");
  try {
    core.shutdown();
  }
 catch (  final Exception inner) {
    if (e != null) {
      inner.addSuppressed(e);
    }
    e=inner;
  }
  if (e != null) {
    throw e;
  }
}
