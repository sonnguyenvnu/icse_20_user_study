private boolean same(Class<?>[] declaredTypes,Class<?>[] actualTypes){
  if (declaredTypes.length == actualTypes.length) {
    for (int i=0; i < actualTypes.length; i++) {
      if (actualTypes[i] == NULL.class) {
        continue;
      }
      if (TextUtils.equals(wrapper(declaredTypes[i]).getName(),wrapper(actualTypes[i]).getName())) {
        continue;
      }
      return false;
    }
    return true;
  }
 else {
    return false;
  }
}
