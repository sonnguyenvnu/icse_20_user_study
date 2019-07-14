/** 
 * Creates a  {@link JSONObject} listing all values human readablefrom the provided Configuration instance.
 * @param conf The Configuration to be described.
 * @return A JSONObject with all fields of the given Configuration,with values replaced by constant names.
 */
@NonNull private JSONObject configToJson(@NonNull Configuration conf){
  final JSONObject result=new JSONObject();
  final Map<String,SparseArray<String>> valueArrays=getValueArrays();
  for (  final Field f : conf.getClass().getFields()) {
    try {
      if (!Modifier.isStatic(f.getModifiers())) {
        final String fieldName=f.getName();
        try {
          if (f.getType().equals(int.class)) {
            result.put(fieldName,getFieldValueName(valueArrays,conf,f));
          }
 else           if (f.get(conf) != null) {
            result.put(fieldName,f.get(conf));
          }
        }
 catch (        JSONException e) {
          ACRA.log.w(LOG_TAG,"Could not collect configuration field " + fieldName,e);
        }
      }
    }
 catch (    @NonNull IllegalArgumentException e) {
      ACRA.log.e(LOG_TAG,"Error while inspecting device configuration: ",e);
    }
catch (    @NonNull IllegalAccessException e) {
      ACRA.log.e(LOG_TAG,"Error while inspecting device configuration: ",e);
    }
  }
  return result;
}
