private static void logHttpHeaders(Map<String,List<String>> headers){
  final StringBuilder sb=new StringBuilder();
  if (headers != null) {
    for (    Map.Entry<String,List<String>> entry : headers.entrySet()) {
      final String key=entry.getKey();
      final List<String> values=entry.getValue();
      if (values != null) {
        for (        String value : values) {
          sb.append(key).append('=').append(value).append('\n');
        }
      }
    }
    Timber.v("HTTP: headers\n" + sb.toString());
  }
}
