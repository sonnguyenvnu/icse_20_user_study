@Override public String getFormatRegex(PropertyIdValue pid){
  List<SnakGroup> specs=getSingleConstraint(pid,FORMAT_CONSTRAINT_QID);
  if (specs != null) {
    List<Value> regexes=findValues(specs,FORMAT_REGEX_PID);
    if (!regexes.isEmpty()) {
      return ((StringValue)regexes.get(0)).getString();
    }
  }
  return null;
}
