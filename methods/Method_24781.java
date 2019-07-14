protected static DefaultListModel<CompletionCandidate> filterPredictions(List<CompletionCandidate> candidates){
  Messages.log("* filterPredictions");
  DefaultListModel<CompletionCandidate> defListModel=new DefaultListModel<>();
  if (candidates.isEmpty())   return defListModel;
  if (candidates.get(0).getElementName().equals(candidates.get(candidates.size() - 1).getElementName())) {
    log("All CC are methods only: " + candidates.get(0).getElementName());
    for (int i=0; i < candidates.size(); i++) {
      CompletionCandidate cc=candidates.get(i).withRegeneratedCompString();
      candidates.set(i,cc);
      defListModel.addElement(cc);
    }
  }
 else {
    boolean ignoredSome=false;
    for (int i=0; i < candidates.size(); i++) {
      if (i > 0 && (candidates.get(i).getElementName().equals(candidates.get(i - 1).getElementName()))) {
        if (candidates.get(i).getType() == CompletionCandidate.LOCAL_METHOD || candidates.get(i).getType() == CompletionCandidate.PREDEF_METHOD) {
          CompletionCandidate cc=candidates.get(i - 1);
          String label=cc.getLabel();
          int x=label.lastIndexOf(')');
          String newLabel;
          if (candidates.get(i).getType() == CompletionCandidate.PREDEF_METHOD) {
            newLabel=(cc.getLabel().contains("<html>") ? "<html>" : "") + cc.getElementName() + "(...)" + label.substring(x + 1);
          }
 else {
            newLabel=cc.getElementName() + "(...)" + label.substring(x + 1);
          }
          String newCompString=cc.getElementName() + "(";
          candidates.set(i - 1,cc.withLabelAndCompString(newLabel,newCompString));
          ignoredSome=true;
          continue;
        }
      }
      defListModel.addElement(candidates.get(i));
    }
    if (ignoredSome) {
      log("Some suggestions hidden");
    }
  }
  return defListModel;
}
