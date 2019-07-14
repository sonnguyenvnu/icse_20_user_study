/** 
 * @webref doublelist:method
 * @brief Check if a number is a part of the list
 */
public boolean hasValue(double value){
  if (Double.isNaN(value)) {
    for (int i=0; i < count; i++) {
      if (Double.isNaN(data[i])) {
        return true;
      }
    }
  }
 else {
    for (int i=0; i < count; i++) {
      if (data[i] == value) {
        return true;
      }
    }
  }
  return false;
}
