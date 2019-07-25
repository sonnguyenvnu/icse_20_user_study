public void report(String message,String macro){
  if (myReported.contains(macro))   return;
  myReported.add(macro);
  for (  PathMacrosProvider p : myMacrosProviders) {
    p.report(message,macro);
  }
}
