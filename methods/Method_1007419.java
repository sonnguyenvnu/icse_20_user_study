/** 
 * Adds the.
 * @param event the event
 * @param path the path
 */
public void add(WatchEvent<Path> event,Path path){
  eventQueue.offer(new Event(event,path));
}
