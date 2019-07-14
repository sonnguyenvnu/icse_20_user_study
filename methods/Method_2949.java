/** 
 * ????
 * @param limit             ?????
 * @param keepNonProjective ?????
 * @param labeled
 * @param rootFirst         ???root?????
 * @param lowerCased
 * @param maps              feature id map
 * @return
 * @throws Exception
 */
public ArrayList<Instance> readData(int limit,boolean keepNonProjective,boolean labeled,boolean rootFirst,boolean lowerCased,IndexMaps maps) throws IOException {
  HashMap<String,Integer> wordMap=maps.getWordId();
  ArrayList<Instance> instanceList=new ArrayList<Instance>();
  String line;
  ArrayList<Integer> tokens=new ArrayList<Integer>();
  ArrayList<Integer> tags=new ArrayList<Integer>();
  ArrayList<Integer> cluster4Ids=new ArrayList<Integer>();
  ArrayList<Integer> cluster6Ids=new ArrayList<Integer>();
  ArrayList<Integer> clusterIds=new ArrayList<Integer>();
  HashMap<Integer,Edge> goldDependencies=new HashMap<Integer,Edge>();
  int sentenceCounter=0;
  while ((line=fileReader.readLine()) != null) {
    line=line.trim();
    if (line.length() == 0) {
      if (tokens.size() > 0) {
        sentenceCounter++;
        if (!rootFirst) {
          for (          Edge edge : goldDependencies.values()) {
            if (edge.headIndex == 0)             edge.headIndex=tokens.size() + 1;
          }
          tokens.add(0);
          tags.add(0);
          cluster4Ids.add(0);
          cluster6Ids.add(0);
          clusterIds.add(0);
        }
        Sentence currentSentence=new Sentence(tokens,tags,cluster4Ids,cluster6Ids,clusterIds);
        Instance instance=new Instance(currentSentence,goldDependencies);
        if (keepNonProjective || !instance.isNonprojective())         instanceList.add(instance);
        goldDependencies=new HashMap<Integer,Edge>();
        tokens=new ArrayList<Integer>();
        tags=new ArrayList<Integer>();
        cluster4Ids=new ArrayList<Integer>();
        cluster6Ids=new ArrayList<Integer>();
        clusterIds=new ArrayList<Integer>();
      }
 else {
        goldDependencies=new HashMap<Integer,Edge>();
        tokens=new ArrayList<Integer>();
        tags=new ArrayList<Integer>();
        cluster4Ids=new ArrayList<Integer>();
        cluster6Ids=new ArrayList<Integer>();
        clusterIds=new ArrayList<Integer>();
      }
      if (sentenceCounter >= limit) {
        System.out.println("buffer full..." + instanceList.size());
        break;
      }
    }
 else {
      String[] cells=line.split("\t");
      if (cells.length < 8)       throw new IllegalArgumentException("invalid conll format");
      int wordIndex=Integer.parseInt(cells[0]);
      String word=cells[1].trim();
      if (lowerCased)       word=word.toLowerCase();
      String pos=cells[3].trim();
      int wi=getId(word,wordMap);
      int pi=getId(pos,wordMap);
      tags.add(pi);
      tokens.add(wi);
      int headIndex=Integer.parseInt(cells[6]);
      String relation=cells[7];
      if (!labeled)       relation="~";
 else       if (relation.equals("_"))       relation="-";
      if (headIndex == 0)       relation="ROOT";
      int ri=getId(relation,wordMap);
      if (headIndex == -1)       ri=-1;
      int[] ids=maps.clusterId(word);
      clusterIds.add(ids[0]);
      cluster4Ids.add(ids[1]);
      cluster6Ids.add(ids[2]);
      if (headIndex >= 0)       goldDependencies.put(wordIndex,new Edge(headIndex,ri));
    }
  }
  if (tokens.size() > 0) {
    if (!rootFirst) {
      for (      int gold : goldDependencies.keySet()) {
        if (goldDependencies.get(gold).headIndex == 0)         goldDependencies.get(gold).headIndex=goldDependencies.size() + 1;
      }
      tokens.add(0);
      tags.add(0);
      cluster4Ids.add(0);
      cluster6Ids.add(0);
      clusterIds.add(0);
    }
    sentenceCounter++;
    Sentence currentSentence=new Sentence(tokens,tags,cluster4Ids,cluster6Ids,clusterIds);
    instanceList.add(new Instance(currentSentence,goldDependencies));
  }
  return instanceList;
}
