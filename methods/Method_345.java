public List<Integer> splitRedPacket(int money,int count){
  List<Integer> moneys=new LinkedList<>();
  if (MAX_MONEY * count <= money) {
    System.err.println("????????? MAX_MONEY=[" + MAX_MONEY + "]");
    return moneys;
  }
  int max=(int)((money / count) * TIMES);
  max=max > MAX_MONEY ? MAX_MONEY : max;
  for (int i=0; i < count; i++) {
    int redPacket=randomRedPacket(money,MIN_MONEY,max,count - i);
    moneys.add(redPacket);
    money-=redPacket;
  }
  return moneys;
}
