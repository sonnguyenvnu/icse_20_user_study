@Override protected Edge makeEdge(Node[] nodeArray,int from,int to){
  LinkedList<String> context=new LinkedList<String>();
  int index=from;
  for (int i=index - 2; i < index + 2 + 1; ++i) {
    Node w=i >= 0 && i < nodeArray.length ? nodeArray[i] : Node.NULL;
    context.add(w.compiledWord + "i" + (i - index));
    context.add(w.label + "i" + (i - index));
  }
  index=to;
  for (int i=index - 2; i < index + 2 + 1; ++i) {
    Node w=i >= 0 && i < nodeArray.length ? nodeArray[i] : Node.NULL;
    context.add(w.compiledWord + "j" + (i - index));
    context.add(w.label + "j" + (i - index));
  }
  context.add(nodeArray[from].compiledWord + '?' + nodeArray[to].compiledWord);
  context.add(nodeArray[from].label + '?' + nodeArray[to].label);
  context.add(nodeArray[from].compiledWord + '?' + nodeArray[to].compiledWord + (from - to));
  context.add(nodeArray[from].label + '?' + nodeArray[to].label + (from - to));
  Node wordBeforeI=from - 1 >= 0 ? nodeArray[from - 1] : Node.NULL;
  Node wordBeforeJ=to - 1 >= 0 ? nodeArray[to - 1] : Node.NULL;
  context.add(wordBeforeI.compiledWord + '@' + nodeArray[from].compiledWord + '?' + nodeArray[to].compiledWord);
  context.add(nodeArray[from].compiledWord + '?' + wordBeforeJ.compiledWord + '@' + nodeArray[to].compiledWord);
  context.add(wordBeforeI.label + '@' + nodeArray[from].label + '?' + nodeArray[to].label);
  context.add(nodeArray[from].label + '?' + wordBeforeJ.label + '@' + nodeArray[to].label);
  List<Pair<String,Double>> pairList=model.predict(context.toArray(new String[0]));
  Pair<String,Double> maxPair=new Pair<String,Double>("null",-1.0);
  for (  Pair<String,Double> pair : pairList) {
    if (pair.getValue() > maxPair.getValue() && !"null".equals(pair.getKey())) {
      maxPair=pair;
    }
  }
  return new Edge(from,to,maxPair.getKey(),(float)-Math.log(maxPair.getValue()));
}
