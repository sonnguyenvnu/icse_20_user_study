public static Class<?> getBuilderClass(Class<?> clazz,JSONType type){
  if (clazz != null && clazz.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
    return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
  }
  if (type == null) {
    return null;
  }
  Class<?> builderClass=type.builder();
  if (builderClass == Void.class) {
    return null;
  }
  return builderClass;
}
