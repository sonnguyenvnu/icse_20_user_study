protected void init(){
  if (segments != null) {
    return;
  }
  if ("*".equals(path)) {
    this.segments=new Segment[]{WildCardSegment.instance};
  }
 else {
    JSONPathParser parser=new JSONPathParser(path);
    this.segments=parser.explain();
    this.hasRefSegment=parser.hasRefSegment;
  }
}
