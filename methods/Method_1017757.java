private String convert(List<Integer> codePoints){
  StringBuilder s=new StringBuilder();
  codePoints.forEach(s::appendCodePoint);
  return s.toString();
}
