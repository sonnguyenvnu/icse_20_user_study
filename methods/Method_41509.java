/** 
 * <p> Updates the <code>DailyTimeIntervalTrigger</code>'s state based on the MISFIRE_INSTRUCTION_XXX that was selected when the <code>DailyTimeIntervalTrigger</code> was created. </p> <p> If the misfire instruction is set to MISFIRE_INSTRUCTION_SMART_POLICY, then the following scheme will be used: <br> <ul> <li>The instruction will be interpreted as <code>MISFIRE_INSTRUCTION_FIRE_ONCE_NOW</code> </ul> </p>
 */
@Override public void updateAfterMisfire(org.quartz.Calendar cal){
  int instr=getMisfireInstruction();
  if (instr == Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY)   return;
  if (instr == MISFIRE_INSTRUCTION_SMART_POLICY) {
    instr=MISFIRE_INSTRUCTION_FIRE_ONCE_NOW;
  }
  if (instr == MISFIRE_INSTRUCTION_DO_NOTHING) {
    Date newFireTime=getFireTimeAfter(new Date());
    while (newFireTime != null && cal != null && !cal.isTimeIncluded(newFireTime.getTime())) {
      newFireTime=getFireTimeAfter(newFireTime);
    }
    setNextFireTime(newFireTime);
  }
 else   if (instr == MISFIRE_INSTRUCTION_FIRE_ONCE_NOW) {
    setNextFireTime(new Date());
  }
}
