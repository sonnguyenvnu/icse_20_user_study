/** 
 * key ? value ? -1 ???????;<br> ??????value???-1<br> ??????<br>
 * @param key
 * @param value
 */
public void put(int key,int value){
  if (key == -1 || value == -1) {
    System.out.println(" -1 is kept ");
  }
  int size=keys.length;
  int index=-1;
  for (int i=0; i < size; i++) {
    if (keys[i] == key || keys[i] == -1) {
      keys[i]=key;
      index=i;
      break;
    }
  }
  if (index == -1) {
    int[] newKeys=new int[size + 1];
    int[] newValues=new int[size + 1];
    System.arraycopy(keys,0,newKeys,0,size);
    System.arraycopy(values,0,newValues,0,size);
    this.keys=newKeys;
    this.values=newValues;
    index=size;
    this.keys[index]=key;
  }
  values[index]=value;
}
