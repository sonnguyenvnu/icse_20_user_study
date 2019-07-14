/** 
 * line format: TCP: inuse 454 orphan 0 tw 159620 alloc 454 mem 79
 */
public void parse(String line,String timeKey) throws Exception {
  if (line.startsWith(FLAG)) {
    String[] items=line.split("\\s+");
    for (int i=0; i < items.length; ++i) {
      if (items[i].equals("inuse")) {
        established=NumberUtils.toInt(items[i + 1]);
      }
 else       if (items[i].equals("orphan")) {
        orphan=NumberUtils.toInt(items[i + 1]);
      }
 else       if (items[i].equals("tw")) {
        timeWait=NumberUtils.toInt(items[i + 1]);
      }
    }
  }
}
