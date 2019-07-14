@SuppressWarnings("unchecked") protected void updateList(String pattern){
  int patternLength=pattern.length();
  if (patternLength == 0) {
    openTypeView.updateList(Collections.emptyMap());
  }
 else {
    executor.execute(() -> {
      openTypeView.showWaitCursor();
      Pattern regExpPattern=createRegExpPattern(pattern);
      Map<String,Collection<Container.Entry>> result=new HashMap<>();
      try {
        for (        Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
          if (futureIndexes.isDone()) {
            Indexes indexes=futureIndexes.get();
            String key=String.valueOf(indexes.hashCode()) + "***" + pattern;
            Map<String,Collection> matchingEntries=cache.get(key);
            if (matchingEntries != null) {
              for (              Map.Entry<String,Collection> mapEntry : matchingEntries.entrySet()) {
                Collection<Container.Entry> collection=result.get(mapEntry.getKey());
                if (collection == null) {
                  result.put(mapEntry.getKey(),collection=new HashSet<>());
                }
                collection.addAll(mapEntry.getValue());
              }
            }
 else {
              Map<String,Collection> index=indexes.getIndex("typeDeclarations");
              if ((index != null) && !index.isEmpty()) {
                matchingEntries=new HashMap<>();
                if (patternLength == 1) {
                  match(pattern.charAt(0),index,matchingEntries);
                }
 else {
                  String lastKey=key.substring(0,patternLength - 1);
                  Map<String,Collection> lastResult=cache.get(lastKey);
                  if (lastResult != null) {
                    match(regExpPattern,lastResult,matchingEntries);
                  }
 else {
                    match(regExpPattern,index,matchingEntries);
                  }
                }
                cache.put(key,matchingEntries);
                for (                Map.Entry<String,Collection> mapEntry : matchingEntries.entrySet()) {
                  Collection<Container.Entry> collection=result.get(mapEntry.getKey());
                  if (collection == null) {
                    result.put(mapEntry.getKey(),collection=new HashSet<>());
                  }
                  collection.addAll(mapEntry.getValue());
                }
              }
            }
          }
        }
      }
 catch (      Exception e) {
        assert ExceptionUtil.printStackTrace(e);
      }
      SwingUtilities.invokeLater(() -> {
        openTypeView.hideWaitCursor();
        openTypeView.updateList(result);
      }
);
    }
);
  }
}
