public void removeChannel(Channel channel){
  channels.remove(channel);
  String key=channel.remoteAddress().toString();
  if (attrDelayTime < 0) {
    appNames.remove(key);
    return;
  }
  try {
    executorService.schedule(() -> {
      appNames.remove(key);
    }
,attrDelayTime,TimeUnit.MILLISECONDS);
  }
 catch (  RejectedExecutionException ignored) {
  }
}
