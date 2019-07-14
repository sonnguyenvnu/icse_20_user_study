/** 
 * <p> Store the given <code> {@link org.quartz.Calendar}</code>. </p>
 * @param calendar The <code>Calendar</code> to be stored.
 * @param replaceExisting If <code>true</code>, any <code>Calendar</code> existing in the <code>JobStore</code> with the same name & group should be over-written.
 * @param updateTriggers If <code>true</code>, any <code>Trigger</code>s existing in the <code>JobStore</code> that reference an existing Calendar with the same name with have their next fire time re-computed with the new <code>Calendar</code>.
 * @throws ObjectAlreadyExistsException if a <code>Calendar</code> with the same name already exists, and replaceExisting is set to false.
 */
public void storeCalendar(String name,Calendar calendar,boolean replaceExisting,boolean updateTriggers) throws ObjectAlreadyExistsException {
  calendar=(Calendar)calendar.clone();
synchronized (lock) {
    Object obj=calendarsByName.get(name);
    if (obj != null && !replaceExisting) {
      throw new ObjectAlreadyExistsException("Calendar with name '" + name + "' already exists.");
    }
 else     if (obj != null) {
      calendarsByName.remove(name);
    }
    calendarsByName.put(name,calendar);
    if (obj != null && updateTriggers) {
      for (      TriggerWrapper tw : getTriggerWrappersForCalendar(name)) {
        OperableTrigger trig=tw.getTrigger();
        boolean removed=timeTriggers.remove(tw);
        trig.updateWithNewCalendar(calendar,getMisfireThreshold());
        if (removed) {
          timeTriggers.add(tw);
        }
      }
    }
  }
}
