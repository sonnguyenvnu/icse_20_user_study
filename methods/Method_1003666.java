/** 
 * Adds the number of milliseconds of elapsed time between  {@link Request#getTimestamp()} and when the response is ready to be sent.<p> The timer is stopped, and the header added, by  {@link Response#beforeSend(ratpack.func.Action)}. This means that the time value is the elapsed time, commonly referred to as wall clock time, and not CPU time. Similarly, it does not include the time to actually start sending data out over the socket. It effectively times the application processing. <p> The value is in milliseconds, accurate to 5 decimal places.
 * @param ctx the handling context.
 */
@Override public void handle(Context ctx){
  Response response=ctx.getResponse();
  response.beforeSend(m -> {
    Clock clock=ctx.get(Clock.class);
    Instant start=ctx.getRequest().getTimestamp();
    long nanos=start.until(Instant.now(clock),ChronoUnit.NANOS);
    BigDecimal diffNanos=new BigDecimal(nanos);
    BigDecimal diffMillis=diffNanos.divide(NANOS_IN_MILLIS,5,RoundingMode.UP);
    m.getHeaders().set(HEADER_NAME,diffMillis.toString());
  }
);
  ctx.next();
}
