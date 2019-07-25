void main(){
  String s="abcdef2323";
{
    Pattern _pattern_0=REGEXP_jzh4fq_a0a0b0b;
    Matcher _matcher_0=_pattern_0.matcher(s);
    if (_matcher_0.find()) {
      System.out.println(_matcher_0.group(1));
    }
  }
}
