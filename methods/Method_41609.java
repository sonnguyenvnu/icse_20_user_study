/** 
 * <p> Get the number of <code> {@link org.quartz.Calendar}</code> s that are stored in the <code>JobsStore</code>. </p>
 */
public int getNumberOfCalendars(){
synchronized (lock) {
    return calendarsByName.size();
  }
}
