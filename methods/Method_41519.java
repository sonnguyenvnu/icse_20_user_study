/** 
 * <p> Updates the <code>SimpleTrigger</code>'s state based on the MISFIRE_INSTRUCTION_XXX that was selected when the <code>SimpleTrigger</code> was created. </p> <p> If the misfire instruction is set to MISFIRE_INSTRUCTION_SMART_POLICY, then the following scheme will be used: <br> <ul> <li>If the Repeat Count is <code>0</code>, then the instruction will be interpreted as <code>MISFIRE_INSTRUCTION_FIRE_NOW</code>.</li> <li>If the Repeat Count is <code>REPEAT_INDEFINITELY</code>, then the instruction will be interpreted as <code>MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT</code>. <b>WARNING:</b> using MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT  with a trigger that has a non-null end-time may cause the trigger to  never fire again if the end-time arrived during the misfire time span.  </li> <li>If the Repeat Count is <code>&gt; 0</code>, then the instruction will be interpreted as <code>MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT</code>. </li> </ul> </p>
 */
@Override public void updateAfterMisfire(Calendar cal){
  int instr=getMisfireInstruction();
  if (instr == Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY)   return;
  if (instr == Trigger.MISFIRE_INSTRUCTION_SMART_POLICY) {
    if (getRepeatCount() == 0) {
      instr=MISFIRE_INSTRUCTION_FIRE_NOW;
    }
 else     if (getRepeatCount() == REPEAT_INDEFINITELY) {
      instr=MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT;
    }
 else {
      instr=MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT;
    }
  }
 else   if (instr == MISFIRE_INSTRUCTION_FIRE_NOW && getRepeatCount() != 0) {
    instr=MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT;
  }
  if (instr == MISFIRE_INSTRUCTION_FIRE_NOW) {
    setNextFireTime(new Date());
  }
 else   if (instr == MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT) {
    Date newFireTime=getFireTimeAfter(new Date());
    while (newFireTime != null && cal != null && !cal.isTimeIncluded(newFireTime.getTime())) {
      newFireTime=getFireTimeAfter(newFireTime);
      if (newFireTime == null)       break;
      java.util.Calendar c=java.util.Calendar.getInstance();
      c.setTime(newFireTime);
      if (c.get(java.util.Calendar.YEAR) > YEAR_TO_GIVEUP_SCHEDULING_AT) {
        newFireTime=null;
      }
    }
    setNextFireTime(newFireTime);
  }
 else   if (instr == MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT) {
    Date newFireTime=getFireTimeAfter(new Date());
    while (newFireTime != null && cal != null && !cal.isTimeIncluded(newFireTime.getTime())) {
      newFireTime=getFireTimeAfter(newFireTime);
      if (newFireTime == null)       break;
      java.util.Calendar c=java.util.Calendar.getInstance();
      c.setTime(newFireTime);
      if (c.get(java.util.Calendar.YEAR) > YEAR_TO_GIVEUP_SCHEDULING_AT) {
        newFireTime=null;
      }
    }
    if (newFireTime != null) {
      int timesMissed=computeNumTimesFiredBetween(nextFireTime,newFireTime);
      setTimesTriggered(getTimesTriggered() + timesMissed);
    }
    setNextFireTime(newFireTime);
  }
 else   if (instr == MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT) {
    Date newFireTime=new Date();
    if (repeatCount != 0 && repeatCount != REPEAT_INDEFINITELY) {
      setRepeatCount(getRepeatCount() - getTimesTriggered());
      setTimesTriggered(0);
    }
    if (getEndTime() != null && getEndTime().before(newFireTime)) {
      setNextFireTime(null);
    }
 else {
      setStartTime(newFireTime);
      setNextFireTime(newFireTime);
    }
  }
 else   if (instr == MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT) {
    Date newFireTime=new Date();
    int timesMissed=computeNumTimesFiredBetween(nextFireTime,newFireTime);
    if (repeatCount != 0 && repeatCount != REPEAT_INDEFINITELY) {
      int remainingCount=getRepeatCount() - (getTimesTriggered() + timesMissed);
      if (remainingCount <= 0) {
        remainingCount=0;
      }
      setRepeatCount(remainingCount);
      setTimesTriggered(0);
    }
    if (getEndTime() != null && getEndTime().before(newFireTime)) {
      setNextFireTime(null);
    }
 else {
      setStartTime(newFireTime);
      setNextFireTime(newFireTime);
    }
  }
}
