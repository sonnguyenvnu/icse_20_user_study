public static Fuzziness build(Object fuzziness){
  if (fuzziness instanceof Fuzziness) {
    return (Fuzziness)fuzziness;
  }
  String string=fuzziness.toString();
  if (AUTO.asString().equalsIgnoreCase(string)) {
    return AUTO;
  }
 else   if (string.toUpperCase(Locale.ROOT).startsWith(AUTO.asString() + ":")) {
    return parseCustomAuto(string);
  }
  return new Fuzziness(string);
}
