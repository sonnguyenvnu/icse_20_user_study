/** 
 * flushes all accumulated events stops listening to SRepository, if no new events are discovered
 * @return result of batching: a list of SRepositoryEvents
 */
public List<SRepositoryEvent> flush(){
synchronized (LOCK) {
    if (!myBatchStarted) {
      return Collections.emptyList();
    }
    List<SRepositoryEvent> result=new ArrayList<>(myEvents);
    myEvents.clear();
    return result;
  }
}
