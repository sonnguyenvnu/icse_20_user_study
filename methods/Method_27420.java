@NonNull public static CharSequence format(final String html){
  if (html == null || html.length() == 0)   return "";
  StringBuilder formatted=new StringBuilder(html);
  strip(formatted,TOGGLE_START,TOGGLE_END);
  strip(formatted,SIGNATURE_START,SIGNATURE_END);
  strip(formatted,REPLY_START,REPLY_END);
  strip(formatted,HIDDEN_REPLY_START,HIDDEN_REPLY_END);
  if (replace(formatted,PARAGRAPH_START,BREAK))   replace(formatted,PARAGRAPH_END,BREAK);
  trim(formatted);
  return formatted;
}
