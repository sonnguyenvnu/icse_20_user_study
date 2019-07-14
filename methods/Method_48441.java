public static ChronoUnit chronoUnit(TimeUnit unit){
switch (unit) {
case NANOSECONDS:
    return ChronoUnit.NANOS;
case MICROSECONDS:
  return ChronoUnit.MICROS;
case MILLISECONDS:
return ChronoUnit.MILLIS;
case SECONDS:
return ChronoUnit.SECONDS;
case MINUTES:
return ChronoUnit.MINUTES;
case HOURS:
return ChronoUnit.HOURS;
case DAYS:
return ChronoUnit.DAYS;
default :
throw new IllegalArgumentException("Cannot convert timeunit");
}
}
