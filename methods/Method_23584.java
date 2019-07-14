/** 
 * Replace the first instance of a particular value 
 */
public boolean replaceValue(double value,double newValue){
  if (Double.isNaN(value)) {
    for (int i=0; i < count; i++) {
      if (Double.isNaN(data[i])) {
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
