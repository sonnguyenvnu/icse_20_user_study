public boolean contains(ColumnGroup g){
  return (g.startColumnIndex >= startColumnIndex && g.startColumnIndex < startColumnIndex + columnSpan);
}
