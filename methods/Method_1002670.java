private void configure(String key,String values){
switch (Ascii.toLowerCase(key)) {
case "exponential":
    exponential(key,values);
  return;
case "fixed":
fixed(key,values);
return;
case "random":
randomBackoff(key,values);
return;
case "jitter":
jitter(key,values);
return;
case "maxattempts":
maxAttempts(key,values);
return;
default :
throw new IllegalArgumentException("Unknown key " + key);
}
}
