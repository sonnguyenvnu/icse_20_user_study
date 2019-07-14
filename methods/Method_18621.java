private static String changeTypeToString(int changeType){
switch (changeType) {
case ChangeType.APPEARED:
    return "APPEARED";
case ChangeType.CHANGED:
  return "CHANGED";
case ChangeType.DISAPPEARED:
return "DISAPPEARED";
case ChangeType.UNSET:
return "UNSET";
default :
throw new RuntimeException("Unknown changeType: " + changeType);
}
}
