@Override public String getHelp(String cmdMarker){
  CommandType cmdType=cmdHandler.getCommandType(cmdMarker);
  StringBuilder sb=new StringBuilder();
  if (cmdType != CommandType.UNKNOWN) {
    sb.append(cmdGroupInfo).append("\n");
    sb.append(leadingSpaces).append(String.format("%-10s",cmdType.getCommandMarker())).append(cmdType.getCommandHelp()).append("\n");
  }
 else {
    sb.append(String.format("??????: %s",cmdMarker)).append("\n");
  }
  sb.append("\n");
  return sb.toString();
}
