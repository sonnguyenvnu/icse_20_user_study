public boolean shouldRequeue(){
  return delayLevelWhenNextConsume != -1;
}
