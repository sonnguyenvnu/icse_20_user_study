/** 
 * Replace the first instance of a particular value 
 */
public boolean replaceValue(float value,float newValue){
  if (Float.isNaN(value)) {
    for (int i=0; i < count; i++) {
      if (Float.isNaN(data[i])) {
        data[i]=newValue;
        return true;
      }
    }
  }
 else {
    int index=index(value);
    if (index != -1) {
      data[index]=newValue;
      return true;
    }
  }
  return false;
}
