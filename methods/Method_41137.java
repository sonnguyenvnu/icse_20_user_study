/** 
 * If the Trigger misfires, use the {@link CronTrigger#MISFIRE_INSTRUCTION_DO_NOTHING} instruction.
 * @return the updated CronScheduleBuilder
 * @see CronTrigger#MISFIRE_INSTRUCTION_DO_NOTHING
 */
public CronScheduleBuilder withMisfireHandlingInstructionDoNothing(){
  misfireInstruction=CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING;
  return this;
}
