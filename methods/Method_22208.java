@NonNull private Map<String,SparseArray<String>> getValueArrays(){
  final Map<String,SparseArray<String>> valueArrays=new HashMap<>();
  final SparseArray<String> hardKeyboardHiddenValues=new SparseArray<>();
  final SparseArray<String> keyboardValues=new SparseArray<>();
  final SparseArray<String> keyboardHiddenValues=new SparseArray<>();
  final SparseArray<String> navigationValues=new SparseArray<>();
  final SparseArray<String> navigationHiddenValues=new SparseArray<>();
  final SparseArray<String> orientationValues=new SparseArray<>();
  final SparseArray<String> screenLayoutValues=new SparseArray<>();
  final SparseArray<String> touchScreenValues=new SparseArray<>();
  final SparseArray<String> uiModeValues=new SparseArray<>();
  for (  final Field f : Configuration.class.getFields()) {
    if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())) {
      final String fieldName=f.getName();
      try {
        if (fieldName.startsWith(PREFIX_HARDKEYBOARDHIDDEN)) {
          hardKeyboardHiddenValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_KEYBOARD)) {
          keyboardValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_KEYBOARDHIDDEN)) {
          keyboardHiddenValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_NAVIGATION)) {
          navigationValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_NAVIGATIONHIDDEN)) {
          navigationHiddenValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_ORIENTATION)) {
          orientationValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_SCREENLAYOUT)) {
          screenLayoutValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_TOUCHSCREEN)) {
          touchScreenValues.put(f.getInt(null),fieldName);
        }
 else         if (fieldName.startsWith(PREFIX_UI_MODE)) {
          uiModeValues.put(f.getInt(null),fieldName);
        }
      }
 catch (      @NonNull IllegalArgumentException e) {
        ACRA.log.w(LOG_TAG,"Error while inspecting device configuration: ",e);
      }
catch (      @NonNull IllegalAccessException e) {
        ACRA.log.w(LOG_TAG,"Error while inspecting device configuration: ",e);
      }
    }
  }
  valueArrays.put(PREFIX_HARDKEYBOARDHIDDEN,hardKeyboardHiddenValues);
  valueArrays.put(PREFIX_KEYBOARD,keyboardValues);
  valueArrays.put(PREFIX_KEYBOARDHIDDEN,keyboardHiddenValues);
  valueArrays.put(PREFIX_NAVIGATION,navigationValues);
  valueArrays.put(PREFIX_NAVIGATIONHIDDEN,navigationHiddenValues);
  valueArrays.put(PREFIX_ORIENTATION,orientationValues);
  valueArrays.put(PREFIX_SCREENLAYOUT,screenLayoutValues);
  valueArrays.put(PREFIX_TOUCHSCREEN,touchScreenValues);
  valueArrays.put(PREFIX_UI_MODE,uiModeValues);
  return valueArrays;
}
