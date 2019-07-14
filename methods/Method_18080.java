@Override public void emitMessage(ComponentsReporter.LogLevel level,String message,int samplingFrequency){
switch (level) {
case WARNING:
    Log.w(CATEGORY,message);
  break;
case ERROR:
Log.e(CATEGORY,message);
break;
case FATAL:
Log.e(CATEGORY,message);
throw new RuntimeException(message);
}
}
