public int size(){
  long currentConsumerIndexBefore;
  long currentProducerIndex;
  long currentConsumerIndexAfter=getHead();
  do {
    currentConsumerIndexBefore=currentConsumerIndexAfter;
    currentProducerIndex=getHead();
    currentConsumerIndexAfter=getTail();
  }
 while (currentConsumerIndexBefore != currentConsumerIndexAfter);
  return (int)(currentProducerIndex - currentConsumerIndexBefore);
}
