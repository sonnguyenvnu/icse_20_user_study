static protected Cell extractCell(OdfTableCell cell,Map<String,Recon> reconMap){
  Serializable value=extractCell(cell);
  if (value != null) {
    Recon recon=null;
    String hyperlink="";
    if (hyperlink != null) {
      String url=hyperlink;
      if (url.startsWith("http://") || url.startsWith("https://")) {
        final String sig="freebase.com/view";
        int i=url.indexOf(sig);
        if (i > 0) {
          String id=url.substring(i + sig.length());
          int q=id.indexOf('?');
          if (q > 0) {
            id=id.substring(0,q);
          }
          int h=id.indexOf('#');
          if (h > 0) {
            id=id.substring(0,h);
          }
          if (reconMap.containsKey(id)) {
            recon=reconMap.get(id);
            recon.judgmentBatchSize++;
          }
 else {
            recon=new Recon(0,null,null);
            recon.service="import";
            recon.match=new ReconCandidate(id,value.toString(),new String[0],100);
            recon.matchRank=0;
            recon.judgment=Judgment.Matched;
            recon.judgmentAction="auto";
            recon.judgmentBatchSize=1;
            recon.addCandidate(recon.match);
            reconMap.put(id,recon);
          }
        }
      }
    }
    return new Cell(value,recon);
  }
 else {
    return null;
  }
}
