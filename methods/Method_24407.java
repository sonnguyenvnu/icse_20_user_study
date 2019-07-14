protected void setModifiedPolyShininess(int first,int last){
  if (first < firstModifiedPolyShininess)   firstModifiedPolyShininess=first;
  if (last > lastModifiedPolyShininess)   lastModifiedPolyShininess=last;
  modifiedPolyShininess=true;
  modified=true;
}
