protected void setModifiedPolyEmissive(int first,int last){
  if (first < firstModifiedPolyEmissive)   firstModifiedPolyEmissive=first;
  if (last > lastModifiedPolyEmissive)   lastModifiedPolyEmissive=last;
  modifiedPolyEmissive=true;
  modified=true;
}
