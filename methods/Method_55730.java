public static void setFunctionMissingAddresses(@Nullable Class<?> capabilitiesClass,int index){
  if (capabilitiesClass == null) {
    long missingCaps=memGetAddress(JNI_NATIVE_INTERFACE + Integer.toUnsignedLong(index) * POINTER_SIZE);
    if (missingCaps != NULL) {
      getAllocator().free(missingCaps);
      memPutAddress(JNI_NATIVE_INTERFACE + Integer.toUnsignedLong(index) * POINTER_SIZE,NULL);
    }
  }
 else {
    int functionCount=getFieldsFromCapabilities(capabilitiesClass).size();
    long missingCaps=getAllocator().malloc(Integer.toUnsignedLong(functionCount) * POINTER_SIZE);
    for (int i=0; i < functionCount; i++) {
      memPutAddress(missingCaps + Integer.toUnsignedLong(i) * POINTER_SIZE,FUNCTION_MISSING_ABORT);
    }
    memPutAddress(JNI_NATIVE_INTERFACE + Integer.toUnsignedLong(index) * POINTER_SIZE,missingCaps);
  }
}
