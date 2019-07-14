private String iterateIndexes(String pattern,Iterable<JanusGraphIndex> indexes){
  StringBuilder sb=new StringBuilder();
  for (  JanusGraphIndex index : indexes) {
    String type=getIndexType(index);
    PropertyKey[] keys=index.getFieldKeys();
    String[][] keyStatus=getKeyStatus(keys,index);
    sb.append(String.format(pattern,index.name(),type,index.isUnique(),index.getBackingIndex(),keyStatus[0][0] + ":",keyStatus[0][1]));
    if (keyStatus.length > 1) {
      for (int i=1; i < keyStatus.length; i++) {
        sb.append(String.format(pattern,"","","","",keyStatus[i][0] + ":",keyStatus[i][1]));
      }
    }
  }
  return sb.toString();
}
