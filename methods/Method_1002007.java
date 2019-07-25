private Map<String,Bucket> buckets(){
  if (buckets == null) {
    final List<String> definition=CType.getDefinition(cl);
    JUtils.LOG("StructureDefinition::run for " + cl);
    JUtils.LOG("def=" + definition);
    JUtils.LOG("first=" + definition.get(0));
    buckets=new LinkedHashMap<String,Bucket>();
    if (definition.get(0).equals("typedef enum")) {
      final String last=definition.get(definition.size() - 1);
      if (last.matches("\\w+") == false) {
        throw new UnsupportedOperationException();
      }
      buckets.put(last,Bucket.buildEnum(last,definition));
      return buckets;
    }
    if (definition.get(0).equals("typedef struct gvplugin_active_textlayout_s") == false && definition.get(0).equals("typedef struct color_s") == false && definition.get(0).equals("typedef struct") == false && definition.get(0).equals("typedef struct pointf_s") == false && definition.get(0).equals("typedef struct gvplugin_active_layout_s") == false && definition.get(0).equals("typedef struct GVCOMMON_s") == false && definition.get(0).equals("struct " + cl.getSimpleName()) == false && definition.get(0).equals("typedef struct " + cl.getSimpleName()) == false && definition.get(0).equals("typedef struct " + cl.getSimpleName().replaceFirst("_t","_s")) == false && definition.get(0).equals("typedef union " + cl.getSimpleName()) == false) {
      throw new IllegalStateException("<struct " + cl.getSimpleName() + "> VERSUS <" + definition.get(0) + ">");
    }
    if (definition.get(1).equals("{") == false) {
      throw new IllegalStateException();
    }
    int last=definition.size() - 1;
    if (definition.get(definition.size() - 2).equals("}") && definition.get(definition.size() - 1).equals(cl.getSimpleName())) {
      last--;
    }
    if (definition.get(last).equals("}") == false) {
      throw new IllegalStateException();
    }
    for (Iterator<String> it=definition.subList(2,last).iterator(); it.hasNext(); ) {
      buckets.putAll(Bucket.buildSome(it));
    }
  }
  return buckets;
}
