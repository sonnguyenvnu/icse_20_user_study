@SuppressWarnings("unchecked") protected void extractMap(final Map target,final Map<String,PropsEntry> map,final String[] profiles,final String[] wildcardPatterns,final String prefix){
  for (  Map.Entry<String,PropsEntry> entry : map.entrySet()) {
    String key=entry.getKey();
    if (wildcardPatterns != null) {
      if (Wildcard.matchOne(key,wildcardPatterns) == -1) {
        continue;
      }
    }
    if (prefix != null) {
      if (!key.startsWith(prefix)) {
        continue;
      }
      key=key.substring(prefix.length());
    }
    if (!target.containsKey(key)) {
      target.put(key,entry.getValue().getValue(profiles));
    }
  }
}
