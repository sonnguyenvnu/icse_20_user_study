/** 
 * Formats the log of executed commands into a string usable for logging purposes. <p> Examples: <ul> <li>TestCommand[SUCCESS][1ms]</li> <li>TestCommand[SUCCESS][1ms], TestCommand[SUCCESS, RESPONSE_FROM_CACHE][1ms]x4</li> <li>TestCommand[TIMEOUT][1ms]</li> <li>TestCommand[FAILURE][1ms]</li> <li>TestCommand[THREAD_POOL_REJECTED][1ms]</li> <li>TestCommand[THREAD_POOL_REJECTED, FALLBACK_SUCCESS][1ms]</li> <li>TestCommand[EMIT, SUCCESS][1ms]</li> <li>TestCommand[EMITx5, SUCCESS][1ms]</li> <li>TestCommand[EMITx5, FAILURE, FALLBACK_EMITx6, FALLBACK_FAILURE][100ms]</li> <li>TestCommand[FAILURE, FALLBACK_SUCCESS][1ms], TestCommand[FAILURE, FALLBACK_SUCCESS, RESPONSE_FROM_CACHE][1ms]x4</li> <li>GetData[SUCCESS][1ms], PutData[SUCCESS][1ms], GetValues[SUCCESS][1ms], GetValues[SUCCESS, RESPONSE_FROM_CACHE][1ms], TestCommand[FAILURE, FALLBACK_FAILURE][1ms], TestCommand[FAILURE, FALLBACK_FAILURE, RESPONSE_FROM_CACHE][1ms]</li> </ul> <p> If a command has a multiplier such as <code>x4</code>, that means this command was executed 4 times with the same events. The time in milliseconds is the sum of the 4 executions. <p> For example, <code>TestCommand[SUCCESS][15ms]x4</code> represents TestCommand being executed 4 times and the sum of those 4 executions was 15ms. These 4 each executed the run() method since <code>RESPONSE_FROM_CACHE</code> was not present as an event. If an EMIT or FALLBACK_EMIT has a multiplier such as <code>x5</code>, that means a <code>HystrixObservableCommand</code> was used and it emitted that number of <code>OnNext</code>s. <p> For example, <code>TestCommand[EMITx5, FAILURE, FALLBACK_EMITx6, FALLBACK_FAILURE][100ms]</code> represents TestCommand executing observably, emitted 5 <code>OnNext</code>s, then an <code>OnError</code>. This command also has an Observable fallback, and it emits 6 <code>OnNext</code>s, then an <code>OnCompleted</code>.
 * @return String request log or "Unknown" if unable to instead of throwing an exception.
 */
public String getExecutedCommandsAsString(){
  try {
    LinkedHashMap<String,Integer> aggregatedCommandsExecuted=new LinkedHashMap<String,Integer>();
    Map<String,Integer> aggregatedCommandExecutionTime=new HashMap<String,Integer>();
    StringBuilder builder=new StringBuilder();
    int estimatedLength=0;
    for (    HystrixInvokableInfo<?> command : allExecutedCommands) {
      builder.setLength(0);
      builder.append(command.getCommandKey().name());
      List<HystrixEventType> events=new ArrayList<HystrixEventType>(command.getExecutionEvents());
      if (events.size() > 0) {
        Collections.sort(events);
        builder.append("[");
        for (        HystrixEventType event : events) {
switch (event) {
case EMIT:
            int numEmissions=command.getNumberEmissions();
          if (numEmissions > 1) {
            builder.append(event).append("x").append(numEmissions).append(", ");
          }
 else {
            builder.append(event).append(", ");
          }
        break;
case FALLBACK_EMIT:
      int numFallbackEmissions=command.getNumberFallbackEmissions();
    if (numFallbackEmissions > 1) {
      builder.append(event).append("x").append(numFallbackEmissions).append(", ");
    }
 else {
      builder.append(event).append(", ");
    }
  break;
default :
builder.append(event).append(", ");
}
}
builder.setCharAt(builder.length() - 2,']');
builder.setLength(builder.length() - 1);
}
 else {
builder.append("[Executed]");
}
String display=builder.toString();
estimatedLength+=display.length() + 12;
Integer counter=aggregatedCommandsExecuted.get(display);
if (counter != null) {
aggregatedCommandsExecuted.put(display,counter + 1);
}
 else {
aggregatedCommandsExecuted.put(display,1);
}
int executionTime=command.getExecutionTimeInMilliseconds();
if (executionTime < 0) {
executionTime=0;
}
counter=aggregatedCommandExecutionTime.get(display);
if (counter != null && executionTime > 0) {
aggregatedCommandExecutionTime.put(display,aggregatedCommandExecutionTime.get(display) + executionTime);
}
 else {
aggregatedCommandExecutionTime.put(display,executionTime);
}
}
builder.setLength(0);
builder.ensureCapacity(estimatedLength);
for (String displayString : aggregatedCommandsExecuted.keySet()) {
if (builder.length() > 0) {
builder.append(", ");
}
builder.append(displayString);
int totalExecutionTime=aggregatedCommandExecutionTime.get(displayString);
builder.append("[").append(totalExecutionTime).append("ms]");
int count=aggregatedCommandsExecuted.get(displayString);
if (count > 1) {
builder.append("x").append(count);
}
}
return builder.toString();
}
 catch (Exception e) {
logger.error("Failed to create HystrixRequestLog response header string.",e);
return "Unknown";
}
}
