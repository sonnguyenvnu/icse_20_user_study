protected void setModifiedLineColors(int first,int last){
  if (first < firstModifiedLineColor)   firstModifiedLineColor=first;
  if (last > lastModifiedLineColor)   lastModifiedLineColor=last;
  modifiedLineColors=true;
  modified=true;
}
