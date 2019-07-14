/** 
 * If the Trigger misfires, use the  {@link CalendarIntervalTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW} instruction.
 * @return the updated CalendarIntervalScheduleBuilder
 * @see CalendarIntervalTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW
 */
public CalendarIntervalScheduleBuilder withMisfireHandlingInstructionFireAndProceed(){
  misfireInstruction=CalendarIntervalTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW;
  return this;
}
