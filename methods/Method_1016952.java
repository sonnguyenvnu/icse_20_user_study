public int compare(double t){
  int index=n - 1;
  int i;
  boolean found=false;
  for (i=0; i < n && !found; i++)   if (sortx[i] > t) {
    index=i;
    found=true;
  }
  return (index);
}
