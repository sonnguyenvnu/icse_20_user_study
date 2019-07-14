public boolean isPartial(boolean rootFirst){
  for (int i=0; i < sentence.size(); i++) {
    if (rootFirst || i < sentence.size() - 1) {
      if (!goldDependencies.containsKey(i + 1))       return true;
    }
  }
  return false;
}
