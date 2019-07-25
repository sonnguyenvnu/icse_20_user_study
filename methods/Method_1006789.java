public int remove(int key) throws Exception {
  if (key == -1)   throw new Exception(" -1 is kept ");
  int index=-1;
  int size=keys.length;
  for (int i=0; i < size; i++) {
    if (key == keys[i]) {
      index=i;
      break;
    }
  }
  if (index == -1)   return -1;
  int result=values[index];
  this.keys[index]=-1;
  this.values[index]=-1;
  int keySize=keys.length;
  int length=0;
  for (int i=0; i < keySize; i++) {
    if (keys[i] == -1)     length++;
  }
  if (length > capacity * 2) {
    size=size();
    size=size < capacity ? capacity : size;
    int[] newKeys=new int[size];
    int[] newValues=new int[size];
    int j=0;
    for (int i=0; i < keySize; i++) {
      if (keys[i] != -1) {
        newKeys[j]=keys[i];
        newValues[j]=values[i];
        j++;
      }
    }
    for (; j < size; j++) {
      newKeys[j]=-1;
      newValues[j]=-1;
    }
    this.keys=newKeys;
    this.values=newValues;
  }
  return result;
}
