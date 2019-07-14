protected void setModifiedPointAttributes(int first,int last){
  if (first < firstModifiedPointAttribute)   firstModifiedPointAttribute=first;
  if (last > lastModifiedPointAttribute)   lastModifiedPointAttribute=last;
  modifiedPointAttributes=true;
  modified=true;
}
