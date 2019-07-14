@Override protected ArrayList<MapEntry> doInBackground(Editable... params){
  for (int i=0; i < (editText.length() - params[0].length()); i++) {
    if (searchTextLength == 0 || isCancelled())     break;
    searchSubString=editText.subSequence(i,i + params[0].length()).toString();
    if (searchSubString.equalsIgnoreCase(params[0].toString())) {
      nodes.add(new MapEntry(new ImmutableEntry<>(i,i + params[0].length()),lineNumberReader.getLineNumber()));
    }
    try {
      lineNumberReader.skip(params[0].length());
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
  return nodes;
}
