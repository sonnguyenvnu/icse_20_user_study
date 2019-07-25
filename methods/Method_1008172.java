/** 
 * Sets the settings and mappings as a single source.
 */
@SuppressWarnings("unchecked") public CreateIndexRequest source(Map<String,?> source){
  boolean found=false;
  for (  Map.Entry<String,?> entry : source.entrySet()) {
    String name=entry.getKey();
    if (SETTINGS.match(name)) {
      found=true;
      settings((Map<String,Object>)entry.getValue());
    }
 else     if (MAPPINGS.match(name)) {
      found=true;
      Map<String,Object> mappings=(Map<String,Object>)entry.getValue();
      for (      Map.Entry<String,Object> entry1 : mappings.entrySet()) {
        mapping(entry1.getKey(),(Map<String,Object>)entry1.getValue());
      }
    }
 else     if (ALIASES.match(name)) {
      found=true;
      aliases((Map<String,Object>)entry.getValue());
    }
 else {
      IndexMetaData.Custom proto=IndexMetaData.lookupPrototype(name);
      if (proto != null) {
        found=true;
        try {
          customs.put(name,proto.fromMap((Map<String,Object>)entry.getValue()));
        }
 catch (        IOException e) {
          throw new ElasticsearchParseException("failed to parse custom metadata for [{}]",name);
        }
      }
    }
  }
  if (!found) {
    settings(source);
  }
  return this;
}
