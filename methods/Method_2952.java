public ArrayList<CompactTree> readStringData() throws IOException {
  ArrayList<CompactTree> treeSet=new ArrayList<CompactTree>();
  String line;
  ArrayList<String> tags=new ArrayList<String>();
  HashMap<Integer,Pair<Integer,String>> goldDependencies=new HashMap<Integer,Pair<Integer,String>>();
  while ((line=fileReader.readLine()) != null) {
    line=line.trim();
    if (line.length() == 0) {
      if (tags.size() >= 1) {
        CompactTree goldConfiguration=new CompactTree(goldDependencies,tags);
        treeSet.add(goldConfiguration);
      }
      tags=new ArrayList<String>();
      goldDependencies=new HashMap<Integer,Pair<Integer,String>>();
    }
 else {
      String[] splitLine=line.split("\t");
      if (splitLine.length < 8)       throw new IllegalArgumentException("wrong file format");
      int wordIndex=Integer.parseInt(splitLine[0]);
      String pos=splitLine[3].trim();
      tags.add(pos);
      int headIndex=Integer.parseInt(splitLine[6]);
      String relation=splitLine[7];
      if (headIndex == 0) {
        relation="ROOT";
      }
      if (pos.length() > 0)       goldDependencies.put(wordIndex,new Pair<Integer,String>(headIndex,relation));
    }
  }
  if (tags.size() > 0) {
    treeSet.add(new CompactTree(goldDependencies,tags));
  }
  return treeSet;
}
