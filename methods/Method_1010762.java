@Override public void paint(Graphics g,EditorComponent editorComponent,EditorCell cell){
  if (cell != null) {
    for (    Region nextRegion : getHighlightedRegions(cell)) {
      nextRegion.drawWaveUnderCell(g,getColor());
    }
  }
}
