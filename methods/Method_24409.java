protected void setModifiedLineVertices(int first,int last){
  if (first < firstModifiedLineVertex)   firstModifiedLineVertex=first;
  if (last > lastModifiedLineVertex)   lastModifiedLineVertex=last;
  modifiedLineVertices=true;
  modified=true;
}
