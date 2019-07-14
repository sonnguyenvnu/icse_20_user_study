public static String encodeScheme(final String scheme){
  return encodeUriComponent(scheme,JoddCore.encoding,URIPart.SCHEME);
}
