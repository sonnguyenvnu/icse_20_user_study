/** 
 * If the Trigger misfires, use the  {@link DailyTimeIntervalTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW} instruction.
 * @return the updated DailyTimeIntervalScheduleBuilder
 * @see DailyTimeIntervalTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW
 */
public DailyTimeIntervalScheduleBuilder withMisfireHandlingInstructionFireAndProceed(){
  misfireInstruction=CalendarIntervalTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW;
  return this;
}
