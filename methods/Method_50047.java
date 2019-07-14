private static void executeNotificationTask(CommonNotificationTask task) throws IOException {
  if (task == null) {
    return;
  }
  try {
    task.run();
  }
 catch (  IOException e) {
    Timber.e(e,"MMS send received notification, io exception");
    throw e;
  }
}
