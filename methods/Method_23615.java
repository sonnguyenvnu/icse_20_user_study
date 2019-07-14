/** 
 * Replace all instances of a particular value 
 */
public boolean replaceValues(float value,float newValue){
  boolean changed=false;
  if (Float.isNaN(value)) {
    for (int i=0; i < count; i++) {
      if (Float.isNaN(data[i])) {
        data[i]=newValue;
        changed=true;
      }
    }
  }
 else {
    for (int i=0; i < count; i++) {
      if (data[i] == value) {
        data[i]=newValue;
        changed=true;
      }
    }
  }
  return changed;
}
