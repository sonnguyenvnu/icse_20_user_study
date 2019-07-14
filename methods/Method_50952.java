private Element addFilesToDuplicationElement(Document doc,Element duplication,Match match){
  Mark mark;
  for (Iterator<Mark> iterator=match.iterator(); iterator.hasNext(); ) {
    mark=iterator.next();
    Element file=doc.createElement("file");
    file.setAttribute("line",String.valueOf(mark.getBeginLine()));
    file.setAttribute("path",mark.getFilename());
    duplication.appendChild(file);
  }
  return duplication;
}
