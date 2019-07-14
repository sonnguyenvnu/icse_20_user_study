@Override public Pair<String,Integer> call() throws Exception {
  HashMap<String,Integer> wordMap=maps.getWordId();
  line=line.trim();
  String[] wrds=line.split(" ");
  String[] words=new String[wrds.length];
  String[] posTags=new String[wrds.length];
  ArrayList<Integer> tokens=new ArrayList<Integer>();
  ArrayList<Integer> tags=new ArrayList<Integer>();
  ArrayList<Integer> brownCluster4thPrefix=new ArrayList<Integer>();
  ArrayList<Integer> brownCluster6thPrefix=new ArrayList<Integer>();
  ArrayList<Integer> brownClusterFullString=new ArrayList<Integer>();
  int i=0;
  for (  String w : wrds) {
    if (w.length() == 0)     continue;
    int index=w.lastIndexOf(delim);
    String word=w.substring(0,index);
    if (lowerCased)     word=word.toLowerCase();
    String pos=w.substring(index + 1);
    words[i]=word;
    posTags[i++]=pos;
    int wi=-1;
    if (wordMap.containsKey(word))     wi=wordMap.get(word);
    int pi=-1;
    if (wordMap.containsKey(pos))     pi=wordMap.get(pos);
    int[] clusters=maps.clusterId(word);
    brownClusterFullString.add(clusters[0]);
    brownCluster4thPrefix.add(clusters[1]);
    brownCluster6thPrefix.add(clusters[2]);
    tokens.add(wi);
    tags.add(pi);
  }
  if (tokens.size() > 0) {
    if (!rootFirst) {
      tokens.add(0);
      tags.add(0);
      brownClusterFullString.add(0);
      brownCluster4thPrefix.add(0);
      brownCluster6thPrefix.add(0);
    }
    Sentence sentence=new Sentence(tokens,tags,brownCluster4thPrefix,brownCluster6thPrefix,brownClusterFullString);
    Configuration bestParse=parser.parse(sentence,rootFirst,beamWidth,1);
    StringBuilder finalOutput=new StringBuilder();
    for (i=0; i < words.length; i++) {
      String word=words[i];
      String pos=posTags[i];
      int w=i + 1;
      int head=bestParse.state.getHead(w);
      int dep=bestParse.state.getDependent(w);
      String lemma="_";
      String fpos="_";
      if (head == bestParse.state.rootIndex)       head=0;
      String label=head == 0 ? maps.rootString : maps.idWord[dep];
      String output=w + "\t" + word + "\t" + lemma + "\t" + pos + "\t" + fpos + "\t_\t" + head + "\t" + label + "\t_\t_\n";
      finalOutput.append(output);
    }
    if (words.length > 0)     finalOutput.append("\n");
    return new Pair<String,Integer>(finalOutput.toString(),lineNum);
  }
  return new Pair<String,Integer>("",lineNum);
}
