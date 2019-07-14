@GlideOption public static void progressListener(RequestOptions options,ProgressListener listener){
  options.getOptions().set(OPTION_LISTENER,listener);
}
