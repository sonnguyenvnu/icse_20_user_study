private List<Object> parseMetadataPrologueForColumnNames() throws IOException {
  _dimensions=new LinkedList<Dimension>();
  List<String> dimensionNames=new ArrayList<String>();
  while (_line != null) {
    int equal=_line.indexOf('=');
    if (equal < 0 || _line.startsWith("DATA=")) {
      break;
    }
    String savedLine=_line;
    List<String> values=parseMetadataValues(equal + 1,exceptions);
    if (savedLine.startsWith("VALUES(\"")) {
      Dimension dimension=new Dimension();
      dimension.name=savedLine.substring(8,equal - 2);
      dimension.values=values;
      _dimensions.add(dimension);
    }
 else     if (savedLine.startsWith("STUB=")) {
      dimensionNames.addAll(0,values);
    }
 else     if (savedLine.startsWith("HEADING=")) {
      dimensionNames.addAll(values);
    }
    _line=_lnReader.readLine();
  }
  final Map<String,Integer> dimensionNameToOrder=new HashMap<String,Integer>();
  for (int i=0; i < dimensionNames.size(); i++) {
    dimensionNameToOrder.put(dimensionNames.get(i),dimensionNames.size() - i - 1);
  }
  Collections.sort(_dimensions,new Comparator<Dimension>(){
    @Override public int compare(    Dimension d0,    Dimension d1){
      return dimensionNameToOrder.get(d0.name).compareTo(dimensionNameToOrder.get(d1.name));
    }
  }
);
  List<Object> columnNames=new LinkedList<Object>();
  for (int i=_dimensions.size() - 1; i >= 0; i--) {
    columnNames.add(_dimensions.get(i).name);
  }
  columnNames.add("value");
  return columnNames;
}
