private static long invokeConversion(TimeUnit timeUnit,String methodName,long duration){
switch (methodName) {
case "toDays":
    return timeUnit.toDays(duration);
case "toHours":
  return timeUnit.toHours(duration);
case "toMinutes":
return timeUnit.toMinutes(duration);
case "toSeconds":
return timeUnit.toSeconds(duration);
case "toMillis":
return timeUnit.toMillis(duration);
case "toMicros":
return timeUnit.toMicros(duration);
case "toNanos":
return timeUnit.toNanos(duration);
default :
throw new IllegalArgumentException();
}
}
