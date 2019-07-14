/** 
 * Match a file suffix defined by this regular expression: /(\.[A-Za-z~][A-Za-z0-9~]*)*$/ Returns empty string if not found. 
 */
private static String match_suffix(String str){
  String match="";
  boolean read_alpha=false;
  while (str.length() > 0) {
    if (read_alpha) {
      read_alpha=false;
      if (!c_isalpha(str.codePointAt(0)) && '~' != str.codePointAt(0))       match="";
    }
 else     if ('.' == str.codePointAt(0)) {
      read_alpha=true;
      if (match.length() == 0)       match=str;
    }
 else     if (!c_isalnum(str.codePointAt(0)) && '~' != str.codePointAt(0)) {
      match="";
    }
    str=str.substring(1,str.length());
  }
  return match;
}
