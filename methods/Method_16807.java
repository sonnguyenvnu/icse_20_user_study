private static String getString(String pattern){
  pattern=pattern.toLowerCase();
  if (pattern.contains(PathFormat.TIME)) {
    return PathFormat.getTimestamp();
  }
 else   if (pattern.contains(PathFormat.FULL_YEAR)) {
    return PathFormat.getFullYear();
  }
 else   if (pattern.contains(PathFormat.YEAR)) {
    return PathFormat.getYear();
  }
 else   if (pattern.contains(PathFormat.MONTH)) {
    return PathFormat.getMonth();
  }
 else   if (pattern.contains(PathFormat.DAY)) {
    return PathFormat.getDay();
  }
 else   if (pattern.contains(PathFormat.HOUR)) {
    return PathFormat.getHour();
  }
 else   if (pattern.contains(PathFormat.MINUTE)) {
    return PathFormat.getMinute();
  }
 else   if (pattern.contains(PathFormat.SECOND)) {
    return PathFormat.getSecond();
  }
 else   if (pattern.contains(PathFormat.RAND)) {
    return PathFormat.getRandom(pattern);
  }
  return pattern;
}
