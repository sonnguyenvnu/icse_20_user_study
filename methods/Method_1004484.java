public synchronized void update(final String subject,final String group,final long nextSequence){
  final ConsumeQueue queue=getOrCreate(subject,group);
  queue.setNextSequence(nextSequence);
}
