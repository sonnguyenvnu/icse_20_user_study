private Element addCodeSnippet(Document doc,Element duplication,Match match){
  String codeSnipet=match.getSourceCodeSlice();
  if (codeSnipet != null) {
    Element codefragment=doc.createElement("codefragment");
    codefragment.appendChild(doc.createCDATASection(codeSnipet));
    duplication.appendChild(codefragment);
  }
  return duplication;
}
