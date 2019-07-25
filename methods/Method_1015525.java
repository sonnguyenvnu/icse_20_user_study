public void init() throws Exception {
  if (min_interval >= max_interval)   throw new IllegalArgumentException("min_interval (" + min_interval + ") has to be < max_interval (" + max_interval + ")");
  if (check_interval == 0)   check_interval=computeCheckInterval();
 else {
    if (check_interval <= max_interval) {
      log.warn("set check_interval=%d as it is <= max_interval",computeCheckInterval());
      check_interval=computeCheckInterval();
    }
  }
  if (max_interval <= 0)   throw new Exception("max_interval must be > 0");
  transport_supports_multicasting=getTransport().supportsMulticasting();
}
