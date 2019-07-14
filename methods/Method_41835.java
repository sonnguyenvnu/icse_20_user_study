/** 
 * <p> Store the given <code> {@link org.quartz.Calendar}</code>. </p>
 * @param calendar The <code>Calendar</code> to be stored.
 * @param replaceExisting If <code>true</code>, any <code>Calendar</code> existing in the <code>JobStore</code> withthe same name & group should be over-written.
 * @param updateTriggers If <code>true</code>, any <code>Trigger</code>s existing in the <code>JobStore</code> thatreference an existing Calendar with the same name with have their next fire time re-computed with the new <code>Calendar</code>.
 * @throws ObjectAlreadyExistsException if a <code>Calendar</code> with the same name already exists, andreplaceExisting is set to false.
 */
@Override public void storeCalendar(String name,Calendar calendar,boolean replaceExisting,boolean updateTriggers) throws ObjectAlreadyExistsException, JobPersistenceException {
  Calendar clone=(Calendar)calendar.clone();
  lock();
  try {
    Calendar cal=calendarsByName.get(name);
    if (cal != null && replaceExisting == false) {
      throw new ObjectAlreadyExistsException("Calendar with name '" + name + "' already exists.");
    }
 else     if (cal != null) {
      calendarsByName.remove(name);
    }
    Calendar cw=clone;
    calendarsByName.putNoReturn(name,cw);
    if (cal != null && updateTriggers) {
      for (      TriggerWrapper tw : triggerFacade.getTriggerWrappersForCalendar(name)) {
        boolean removed=timeTriggers.remove(tw);
        tw.updateWithNewCalendar(clone,getMisfireThreshold(),triggerFacade);
        if (removed) {
          timeTriggers.add(tw);
        }
      }
    }
  }
  finally {
    unlock();
  }
}
