public Object extract(DefaultJSONParser parser){
  if (parser == null) {
    return null;
  }
  init();
  if (hasRefSegment) {
    Object root=parser.parse();
    return this.eval(root);
  }
  if (segments.length == 0) {
    return parser.parse();
  }
  Context context=null;
  for (int i=0; i < segments.length; ++i) {
    Segment segment=segments[i];
    boolean last=i == segments.length - 1;
    if (context != null && context.object != null) {
      context.object=segment.eval(this,null,context.object);
      continue;
    }
    boolean eval;
    if (!last) {
      Segment nextSegment=segments[i + 1];
      if (segment instanceof PropertySegment && ((PropertySegment)segment).deep && (nextSegment instanceof ArrayAccessSegment || nextSegment instanceof MultiIndexSegment || nextSegment instanceof MultiPropertySegment || nextSegment instanceof SizeSegment || nextSegment instanceof PropertySegment || nextSegment instanceof FilterSegment)) {
        eval=true;
      }
 else       if (nextSegment instanceof ArrayAccessSegment && ((ArrayAccessSegment)nextSegment).index < 0) {
        eval=true;
      }
 else       if (nextSegment instanceof FilterSegment) {
        eval=true;
      }
 else       if (segment instanceof WildCardSegment) {
        eval=true;
      }
 else {
        eval=false;
      }
    }
 else {
      eval=true;
    }
    context=new Context(context,eval);
    segment.extract(this,parser,context);
  }
  return context.object;
}
