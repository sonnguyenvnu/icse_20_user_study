public String projectUpdateUrl(){
  return Uri.parse(project().webProjectUrl()).buildUpon().appendEncodedPath("posts").appendPath(Long.toString(update().id())).toString();
}
