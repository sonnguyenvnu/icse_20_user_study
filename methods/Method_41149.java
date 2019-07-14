/** 
 * If the Trigger misfires, use the  {@link DailyTimeIntervalTrigger#MISFIRE_INSTRUCTION_DO_NOTHING} instruction.
 * @return the updated DailyTimeIntervalScheduleBuilder
 * @see DailyTimeIntervalTrigger#MISFIRE_INSTRUCTION_DO_NOTHING
 */
public DailyTimeIntervalScheduleBuilder withMisfireHandlingInstructionDoNothing(){
  misfireInstruction=DailyTimeIntervalTrigger.MISFIRE_INSTRUCTION_DO_NOTHING;
  return this;
}
