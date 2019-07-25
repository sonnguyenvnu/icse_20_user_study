private double rnd(){
  if (this.rnd == null) {
    this.rnd=new Random(skb.getHistory().hashCode());
  }
  return rnd.nextDouble();
}
