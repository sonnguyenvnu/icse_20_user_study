public static Status convertStatus(int status){
switch (status) {
case 1:
    return Status.PROCESSING;
case 2:
  return Status.COMPLETE;
case 3:
return Status.FAILED;
default :
throw new RuntimeException("Unknown status of bibox deposit: " + status);
}
}
