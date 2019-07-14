/** 
 * @return An event object from recycler pool. It is suggested to use this method to obtain an event object. Thereturned object is blank.
 */
public static Event obtainEvent(){
  return EventPool.sharedInstance().acquire();
}
