/** 
 * Adds markers for a list of ranges in the document.
 * @param ranges The list of ranges in the document.
 * @param markerMap A mapping from line number to <code>Marker</code>.
 * @param color The color to use for the markers.
 */
private void addMarkersForRanges(List<DocumentRange> ranges,Map<Integer,Marker> markerMap,Color color){
  for (  DocumentRange range : ranges) {
    int line=0;
    try {
      line=textArea.getLineOfOffset(range.getStartOffset());
    }
 catch (    BadLocationException ble) {
      continue;
    }
    ParserNotice notice=new MarkedOccurrenceNotice(range,color);
    Integer key=Integer.valueOf(line);
    Marker m=markerMap.get(key);
    if (m == null) {
      m=new Marker(notice);
      m.addMouseListener(listener);
      markerMap.put(key,m);
      add(m);
    }
 else {
      if (!m.containsMarkedOccurence()) {
        m.addNotice(notice);
      }
    }
  }
}
