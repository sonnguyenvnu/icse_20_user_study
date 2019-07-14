public void tick(int n){
  count+=n;
  if (count > total) {
    total=count;
  }
  long elapsed=System.currentTimeMillis() - lastTickTime;
  if (elapsed > 500 || count == total) {
    _.msg_("\r");
    int dlen=(int)Math.ceil(Math.log10((double)total));
    _.msg_(_.percent(count,total) + " (" + _.formatNumber(count,dlen) + " of " + _.formatNumber(total,dlen) + ")");
    int rate;
    if (elapsed > 1) {
      rate=(int)((count - lastCount) / (elapsed / 1000.0));
    }
 else {
      rate=lastRate;
    }
    lastRate=rate;
    _.msg_("   SPEED: " + _.formatNumber(rate,MAX_SPEED_DIGITS) + "/s");
    long totalElapsed=System.currentTimeMillis() - startTime;
    int avgRate;
    if (totalElapsed > 1) {
      avgRate=(int)(count / (totalElapsed / 1000.0));
    }
 else {
      avgRate=lastAvgRate;
    }
    avgRate=avgRate == 0 ? 1 : avgRate;
    _.msg_("   AVG SPEED: " + _.formatNumber(avgRate,MAX_SPEED_DIGITS) + "/s");
    long remain=total - count;
    long remainTime=remain / avgRate * 1000;
    _.msg_("   ETA: " + _.formatTime(remainTime));
    _.msg_("       ");
    lastTickTime=System.currentTimeMillis();
    lastAvgRate=avgRate;
    lastCount=count;
  }
}
