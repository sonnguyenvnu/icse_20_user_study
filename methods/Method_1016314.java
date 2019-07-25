@Override public final String format(){
  long time=System.currentTimeMillis();
  if (Math.abs(time - last_time) < 1000)   return last_format;
synchronized (FORMAT_ISO8601) {
    last_format=FORMAT_ISO8601.format(new Date(time));
    last_time=time;
  }
  return last_format;
}
