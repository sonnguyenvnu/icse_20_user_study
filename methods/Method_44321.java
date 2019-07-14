private static void setupPatchSupport(){
  try {
    Field methodsField=HttpURLConnection.class.getDeclaredField("methods");
    methodsField.setAccessible(true);
    Field modifiersField=Field.class.getDeclaredField("modifiers");
    modifiersField.setAccessible(true);
    modifiersField.setInt(methodsField,methodsField.getModifiers() & ~Modifier.FINAL);
    String[] methods={"GET","POST","HEAD","OPTIONS","PUT","DELETE","TRACE","PATCH"};
    methodsField.set(null,methods);
  }
 catch (  SecurityException|IllegalArgumentException|IllegalAccessException|NoSuchFieldException e) {
    LOGGER.error("Error while setting up PATCH support");
  }
}
