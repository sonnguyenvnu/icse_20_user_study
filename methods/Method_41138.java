/** 
 * If the Trigger misfires, use the {@link CronTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW} instruction.
 * @return the updated CronScheduleBuilder
 * @see CronTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW
 */
public CronScheduleBuilder withMisfireHandlingInstructionFireAndProceed(){
  misfireInstruction=CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW;
  return this;
}
