public static String shortenCfName(BiMap<String,String> shortCfNameMap,String longName) throws PermanentBackendException {
  final String s;
  if (shortCfNameMap.containsKey(longName)) {
    s=shortCfNameMap.get(longName);
    Preconditions.checkNotNull(s);
    logger.debug("Substituted default CF name \"{}\" with short form \"{}\" to reduce HBase KeyValue size",longName,s);
  }
 else {
    if (shortCfNameMap.containsValue(longName)) {
      String fmt="Must use CF long-form name \"%s\" instead of the short-form name \"%s\" when configured with %s=true";
      String msg=String.format(fmt,shortCfNameMap.inverse().get(longName),longName,SHORT_CF_NAMES.getName());
      throw new PermanentBackendException(msg);
    }
    s=longName;
    logger.debug("Kept default CF name \"{}\" because it has no associated short form",s);
  }
  return s;
}
