private static String checkNotEmpty(String arg,PropertyDescriptorField argId) throws IllegalArgumentException {
  if (StringUtils.isBlank(arg)) {
    throw new IllegalArgumentException("Property attribute '" + argId + "' cannot be null or blank");
  }
  return arg;
}
