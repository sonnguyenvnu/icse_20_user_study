private static Status adaptDepostStatus(String state){
switch (state) {
case "confirming":
case "safe":
    return Status.PROCESSING;
case "confirmed":
  return Status.COMPLETE;
case "unknown":
case "orphan":
return Status.FAILED;
default :
return null;
}
}
