@Override public void run(){
  HttpClientStats httpClientStats=((DefaultHttpClient)httpClient).getHttpClientStats();
  gauge(TOTAL_ACTIVE_CONNECTIONS).setValue(httpClientStats.getTotalActiveConnectionCount());
  gauge(TOTAL_IDLE_CONNECTIONS).setValue(httpClientStats.getTotalIdleConnectionCount());
  gauge(TOTAL_CONNECTIONS).setValue(httpClientStats.getTotalConnectionCount());
  httpClientStats.getStatsPerHost().forEach((host,stats) -> {
    gauge(getHostMetricName(host,"total.active.connections")).setValue(stats.getActiveConnectionCount());
    gauge(getHostMetricName(host,"total.idle.connections")).setValue(stats.getIdleConnectionCount());
    gauge(getHostMetricName(host,"total.connections")).setValue(stats.getTotalConnectionCount());
  }
);
}
