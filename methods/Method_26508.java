private static boolean exemptedByName(Name name){
  return Ascii.toLowerCase(name.toString()).startsWith(EXEMPT_PREFIX);
}
