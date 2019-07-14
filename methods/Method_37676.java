public static String encodePath(final String path){
  return encodeUriComponent(path,JoddCore.encoding,URIPart.PATH);
}
