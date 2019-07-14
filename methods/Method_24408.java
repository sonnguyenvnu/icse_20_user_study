protected void setModifiedPolyAttrib(VertexAttribute attrib,int first,int last){
  if (first < attrib.firstModified)   attrib.firstModified=first;
  if (last > attrib.lastModified)   attrib.lastModified=last;
  attrib.modified=true;
  modified=true;
}
