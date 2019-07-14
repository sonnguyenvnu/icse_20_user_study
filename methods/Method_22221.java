private void collectFlags(@NonNull Display display,@NonNull JSONObject container) throws JSONException {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    final SparseArray<String> flagNames=new SparseArray<>();
    final int flags=display.getFlags();
    for (    Field field : display.getClass().getFields()) {
      if (field.getName().startsWith("FLAG_")) {
        try {
          flagNames.put(field.getInt(null),field.getName());
        }
 catch (        IllegalAccessException ignored) {
        }
      }
    }
    container.put("flags",activeFlags(flagNames,flags));
  }
}
