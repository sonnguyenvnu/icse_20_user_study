public static void running(final SocketServer server,final Runnable runnable) throws Exception {
  doRunning(runner(checkNotNull(server)),checkNotNull(runnable));
}
