public String generateInstanceId() throws SchedulerException {
  try {
    return InetAddress.getLocalHost().getHostName() + System.currentTimeMillis();
  }
 catch (  Exception e) {
    throw new SchedulerException("Couldn't get host name!",e);
  }
}
