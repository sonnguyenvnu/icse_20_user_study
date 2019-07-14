private Info createInfo(long millis){
  long periodStart=millis & (0xffffffffL << 32);
  Info info=new Info(iZone,periodStart);
  long end=periodStart | 0xffffffffL;
  Info chain=info;
  while (true) {
    long next=iZone.nextTransition(periodStart);
    if (next == periodStart || next > end) {
      break;
    }
    periodStart=next;
    chain=(chain.iNextInfo=new Info(iZone,periodStart));
  }
  return info;
}
