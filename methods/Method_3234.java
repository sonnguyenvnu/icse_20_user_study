static String humanTime(long ms){
  StringBuffer text=new StringBuffer("");
  if (ms > DAY) {
    text.append(ms / DAY).append(" d ");
    ms%=DAY;
  }
  if (ms > HOUR) {
    text.append(ms / HOUR).append(" h ");
    ms%=HOUR;
  }
  if (ms > MINUTE) {
    text.append(ms / MINUTE).append(" m ");
    ms%=MINUTE;
  }
  if (ms > SECOND) {
    long s=ms / SECOND;
    if (s < 10) {
      text.append('0');
    }
    text.append(s).append(" s ");
  }
  return text.toString();
}
