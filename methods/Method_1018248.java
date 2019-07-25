@Override protected void run(String method) throws IOException {
  String text=getString("text");
  int length=getInt("length",86);
  if (text == null || "".equals(text)) {
    response(STATUS_INVALID_ARGS,"Invalid Arguments");
    return;
  }
  JcsegGlobalResource resourcePool=(JcsegGlobalResource)globalResource;
  JcsegTokenizerEntry tokenizerEntry=resourcePool.getTokenizerEntry("extractor");
  if (tokenizerEntry == null) {
    response(STATUS_INVALID_ARGS,"can't find tokenizer instance \"extractor\"");
    return;
  }
  try {
    ISegment seg=SegmentFactory.createJcseg(JcsegTaskConfig.COMPLEX_MODE,new Object[]{tokenizerEntry.getConfig(),tokenizerEntry.getDict()});
    SummaryExtractor extractor=new TextRankSummaryExtractor(seg,new SentenceSeg());
    long s_time=System.nanoTime();
    String summary=extractor.getSummaryFromString(text,length);
    double c_time=(System.nanoTime() - s_time) / 1E9;
    Map<String,Object> map=new HashMap<String,Object>();
    DecimalFormat df=new DecimalFormat("0.00000");
    map.put("took",Float.valueOf(df.format(c_time)));
    map.put("summary",summary);
    response(STATUS_OK,map);
  }
 catch (  JcsegException e) {
    response(STATUS_INTERNEL_ERROR,"Internal error...");
  }
}
