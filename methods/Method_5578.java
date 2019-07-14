private static long parseTimecode(Matcher matcher,int groupOffset){
  long timestampMs=Long.parseLong(matcher.group(groupOffset + 1)) * 60 * 60 * 1000;
  timestampMs+=Long.parseLong(matcher.group(groupOffset + 2)) * 60 * 1000;
  timestampMs+=Long.parseLong(matcher.group(groupOffset + 3)) * 1000;
  timestampMs+=Long.parseLong(matcher.group(groupOffset + 4));
  return timestampMs * 1000;
}
