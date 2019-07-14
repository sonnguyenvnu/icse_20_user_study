protected void setModifiedPolyAmbient(int first,int last){
  if (first < firstModifiedPolyAmbient)   firstModifiedPolyAmbient=first;
  if (last > lastModifiedPolyAmbient)   lastModifiedPolyAmbient=last;
  modifiedPolyAmbient=true;
  modified=true;
}
