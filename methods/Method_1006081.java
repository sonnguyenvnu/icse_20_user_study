/** 
 * The log event will be forwarded to the  {@link LogMessages} archive.
 */
@Override public void append(LogEvent rawEvent){
  ApplicationInsightsLogEvent event=new ApplicationInsightsLogEvent(rawEvent);
  Telemetry telemetry;
  if (event.isException()) {
    ExceptionTelemetry exceptionTelemetry=new ExceptionTelemetry(event.getException());
    exceptionTelemetry.getProperties().put("Message",rawEvent.getMessage().getFormattedMessage());
    exceptionTelemetry.setSeverityLevel(event.getNormalizedSeverityLevel());
    telemetry=exceptionTelemetry;
  }
 else {
    TraceTelemetry traceTelemetry=new TraceTelemetry(event.getMessage());
    traceTelemetry.setSeverityLevel(event.getNormalizedSeverityLevel());
    telemetry=traceTelemetry;
  }
  telemetry.getContext().getProperties().putAll(event.getCustomParameters());
  Globals.getTelemetryClient().ifPresent(client -> client.track(telemetry));
}
