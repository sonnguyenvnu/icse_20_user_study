public static void matchAndAddDocumentRange(Pattern pattern,String text,int start,int end,List<DocumentRange> ranges){
  if (pattern.matcher(text).matches()) {
    ranges.add(new DocumentRange(start,end));
  }
}
