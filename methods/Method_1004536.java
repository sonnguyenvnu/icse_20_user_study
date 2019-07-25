@Override public void run(){
  if (cache == null)   return;
  List<SqlTileWriterExt.SourceCount> sources=cache.getSources();
  final StringBuilder sb=new StringBuilder("Source: tile count\n");
  if (sources.isEmpty())   sb.append("None");
  for (  final SqlTileWriterExt.SourceCount sourceCount : sources) {
    sb.append("Source ").append(sourceCount.source);
    sb.append(": count=").append(sourceCount.rowCount);
    sb.append("; minsize=").append(sourceCount.sizeMin);
    sb.append("; maxsize=").append(sourceCount.sizeMax);
    sb.append("; totalsize=").append(sourceCount.sizeTotal);
    sb.append("; avgsize=").append(sourceCount.sizeAvg);
    sb.append("\n");
  }
  long expired=0;
  if (cache != null)   expired=cache.getRowCountExpired();
  sb.append("Expired tiles: " + expired);
  this.runOnUiThread(new Runnable(){
    @Override public void run(){
      try {
        TextView tv=findViewById(R.id.cacheStats);
        if (tv != null) {
          tv.setText(sb.toString());
        }
      }
 catch (      Exception ex) {
      }
    }
  }
);
}
