@JsonCreator static BucketStrategy create(final String strategy){
switch (strategy) {
case "start":
    return START;
case "end":
  return END;
default :
throw new IllegalArgumentException(strategy);
}
}
