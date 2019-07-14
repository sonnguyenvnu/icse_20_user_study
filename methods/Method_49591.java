private Analyzer analyzerFor(final String analyzerName){
  if (!analyzers.containsKey(analyzerName)) {
    try {
      final Class classDefinition=Class.forName(analyzerName);
      analyzers.put(analyzerName,(Analyzer)classDefinition.newInstance());
    }
 catch (    Exception e) {
      throw new RuntimeException("Analyzer cannot be instanciated for class " + analyzerName,e);
    }
  }
  return analyzers.get(analyzerName);
}
