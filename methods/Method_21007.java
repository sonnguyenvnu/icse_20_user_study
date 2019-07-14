public @NonNull String truncatedBody(){
  try {
    String str=Html.fromHtml(body()).toString();
    if (str.length() > TRUNCATED_BODY_LENGTH) {
      str=str.substring(0,TRUNCATED_BODY_LENGTH - 1) + "\u2026";
    }
    return str;
  }
 catch (  final @NonNull NullPointerException ignore) {
  }
  return "";
}
