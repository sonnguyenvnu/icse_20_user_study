@Override protected void append(ILoggingEvent event){
  if (Objects.isNull(logViewer))   return;
  String message=event.getFormattedMessage();
  String level=event.getLevel().toString();
  if (Objects.isNull(message)) {
    return;
  }
  if (event.getLevel() == Level.ERROR) {
    ObservableList<String> styleClass=logShowHider.getStyleClass();
    if (!styleClass.contains("red-label")) {
      styleClass.add("red-label");
    }
  }
  final String finalMessage=message;
  threadService.buff("logMessager").schedule(() -> {
    threadService.runActionLater(() -> {
      logShortMessage.setText(finalMessage);
    }
);
  }
,1,TimeUnit.SECONDS);
  IThrowableProxy tp=event.getThrowableProxy();
  if (Objects.nonNull(tp) && event.getLevel() == Level.ERROR) {
    String tpMessage=ThrowableProxyUtil.asString(tp);
    message+="\n" + tpMessage;
  }
  if (!message.isEmpty()) {
    MyLog myLog=new MyLog(level,message);
    buffer.add(myLog);
  }
  threadService.buff("logAppender").schedule(() -> {
    final List<MyLog> clone=new LinkedList<>(buffer);
    buffer.clear();
    threadService.runActionLater(() -> {
      logList.addAll(clone);
    }
);
  }
,2,TimeUnit.SECONDS);
}
