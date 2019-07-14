private Element createDuplicationElement(Document doc,Match match){
  Element duplication=doc.createElement("duplication");
  duplication.setAttribute("lines",String.valueOf(match.getLineCount()));
  duplication.setAttribute("tokens",String.valueOf(match.getTokenCount()));
  return duplication;
}
