protected void setModifiedPolyColors(int first,int last){
  if (first < firstModifiedPolyColor)   firstModifiedPolyColor=first;
  if (last > lastModifiedPolyColor)   lastModifiedPolyColor=last;
  modifiedPolyColors=true;
  modified=true;
}
