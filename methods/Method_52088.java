protected boolean isIPv6(final char firstChar,String s,final boolean checkIPv6,final boolean checkIPv4MappedIPv6){
  if (s.length() < 3 || !(isHexCharacter(firstChar) || firstChar == ':') || StringUtils.countMatches(s,':') < 2) {
    return false;
  }
  Matcher matcher=IPV6_PATTERN.matcher(s);
  if (matcher.matches()) {
    boolean zeroSubstitution=false;
    if (s.startsWith("::")) {
      s=s.substring(2);
      zeroSubstitution=true;
    }
 else     if (s.endsWith("::")) {
      s=s.substring(0,s.length() - 2);
      zeroSubstitution=true;
    }
    if (s.endsWith(":")) {
      return false;
    }
    int count=0;
    boolean ipv4Mapped=false;
    String[] parts=s.split(":");
    for (int i=0; i < parts.length; i++) {
      final String part=parts[i];
      if (part.length() == 0) {
        if (zeroSubstitution) {
          return false;
        }
 else {
          zeroSubstitution=true;
        }
        continue;
      }
 else {
        count++;
      }
      try {
        int value=Integer.parseInt(part,16);
        if (value < 0 || value > 65535) {
          return false;
        }
      }
 catch (      NumberFormatException e) {
        if (i != parts.length - 1 || !isIPv4(firstChar,part)) {
          return false;
        }
        ipv4Mapped=true;
      }
    }
    if (zeroSubstitution) {
      if (ipv4Mapped) {
        return checkIPv4MappedIPv6 && 1 <= count && count <= 6;
      }
 else {
        return checkIPv6 && 1 <= count && count <= 7;
      }
    }
 else {
      if (ipv4Mapped) {
        return checkIPv4MappedIPv6 && count == 7;
      }
 else {
        return checkIPv6 && count == 8;
      }
    }
  }
 else {
    return false;
  }
}
