public static AggregationPath parse(String path){
  String[] elements=Strings.tokenizeToStringArray(path,AGG_DELIM);
  List<PathElement> tokens=new ArrayList<>(elements.length);
  String[] tuple=new String[2];
  for (int i=0; i < elements.length; i++) {
    String element=elements[i];
    if (i == elements.length - 1) {
      int index=element.lastIndexOf('[');
      if (index >= 0) {
        if (index == 0 || index > element.length() - 3) {
          throw new AggregationExecutionException("Invalid path element [" + element + "] in path [" + path + "]");
        }
        if (element.charAt(element.length() - 1) != ']') {
          throw new AggregationExecutionException("Invalid path element [" + element + "] in path [" + path + "]");
        }
        tokens.add(new PathElement(element,element.substring(0,index),element.substring(index + 1,element.length() - 1)));
        continue;
      }
      index=element.lastIndexOf('.');
      if (index < 0) {
        tokens.add(new PathElement(element,element,null));
        continue;
      }
      if (index == 0 || index > element.length() - 2) {
        throw new AggregationExecutionException("Invalid path element [" + element + "] in path [" + path + "]");
      }
      tuple=split(element,index,tuple);
      tokens.add(new PathElement(element,tuple[0],tuple[1]));
    }
 else {
      int index=element.lastIndexOf('[');
      if (index >= 0) {
        if (index == 0 || index > element.length() - 3) {
          throw new AggregationExecutionException("Invalid path element [" + element + "] in path [" + path + "]");
        }
        if (element.charAt(element.length() - 1) != ']') {
          throw new AggregationExecutionException("Invalid path element [" + element + "] in path [" + path + "]");
        }
        tokens.add(new PathElement(element,element.substring(0,index),element.substring(index + 1,element.length() - 1)));
        continue;
      }
      tokens.add(new PathElement(element,element,null));
    }
  }
  return new AggregationPath(tokens);
}
