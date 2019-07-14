/** 
 * If the Trigger misfires, use the  {@link Trigger#MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY} instruction.
 * @return the updated CronScheduleBuilder
 * @see Trigger#MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
 */
public CalendarIntervalScheduleBuilder withMisfireHandlingInstructionIgnoreMisfires(){
  misfireInstruction=Trigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY;
  return this;
}
