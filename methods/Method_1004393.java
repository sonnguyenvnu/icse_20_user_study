private void update(BrokerGroupInfo group,double factor,int maxWeight){
  int weight=currentWeight(group);
  weight=Math.min(Math.max((int)(weight * factor),MIN_WEIGHT),maxWeight);
  weights.put(group,weight);
}
