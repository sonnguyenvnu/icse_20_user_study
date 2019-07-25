public boolean subset(Set x){
  Enumeration elements=elements();
  while (elements.hasMoreElements())   if (!x.in(elements.nextElement()))   return false;
  return true;
}
