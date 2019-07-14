/** 
 * Get a  {@link ScheduleBuilder} that is configured to produce a schedule identical to this trigger's schedule.
 * @see #getTriggerBuilder()
 */
@Override public ScheduleBuilder<CronTrigger> getScheduleBuilder(){
  CronScheduleBuilder cb=CronScheduleBuilder.cronSchedule(getCronExpression()).inTimeZone(getTimeZone());
switch (getMisfireInstruction()) {
case MISFIRE_INSTRUCTION_DO_NOTHING:
    cb.withMisfireHandlingInstructionDoNothing();
  break;
case MISFIRE_INSTRUCTION_FIRE_ONCE_NOW:
cb.withMisfireHandlingInstructionFireAndProceed();
break;
}
return cb;
}
