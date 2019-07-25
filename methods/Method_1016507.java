public static String awk(String sentence,final String separator,int count){
  if ((sentence == null) || (separator == null) || (count < 1))   return null;
  int pos;
  while ((count >= 1) && (sentence.length() > 0)) {
    pos=sentence.indexOf(separator);
    if (pos < 0) {
      if (count == 1)       return sentence;
      return null;
    }
    if (count == 1)     return sentence.substring(0,pos);
    sentence=sentence.substring(pos + separator.length());
    count--;
  }
  return null;
}
