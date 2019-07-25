private void process(XWPFParagraph paragraph,T paragraphContainer,boolean pageNumber,String url,List<XmlObject> rListAfterSeparate) throws Exception {
  if (rListAfterSeparate != null) {
    for (    XmlObject oAfterSeparate : rListAfterSeparate) {
      if (oAfterSeparate instanceof CTR) {
        CTR ctr=(CTR)oAfterSeparate;
        XWPFRun run=new XWPFRun(ctr,paragraph);
        visitRun(run,pageNumber,url,paragraphContainer);
      }
 else {
        visitRun(paragraph,oAfterSeparate,paragraphContainer);
      }
    }
  }
}
