private static void submit(final Map<String,String> parameters,final boolean dontWaitForCompletion){
  parameters.put("ts",Long.toString(System.currentTimeMillis()));
  parameters.put("optout",Boolean.toString(isOptOut()));
  try {
    final URL url=new URL("https://" + (isOptOut() ? "esc02" : "esc01") + ".tlapl.us/?" + encode(parameters));
    final HttpURLConnection con=(HttpURLConnection)url.openConnection();
    con.setRequestMethod("HEAD");
    con.getResponseMessage();
    con.disconnect();
  }
 catch (  Exception ignoreCompletely) {
  }
}
