public int size(int label){
  int size=0;
  for (int i=0; i < labels.length; i++)   if (labels[i] == label)   size++;
  return size;
}
