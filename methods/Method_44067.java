private static Status convertStatus(int status){
switch (status) {
case 1:
case 2:
    return Status.PROCESSING;
case 3:
  return Status.COMPLETE;
case 4:
case 5:
return Status.FAILED;
default :
throw new RuntimeException("Unknown status value: " + status);
}
}
