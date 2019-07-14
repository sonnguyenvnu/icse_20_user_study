/** 
 * Add a new entry to the list.
 * @webref doublelist:method
 * @brief Add a new entry to the list
 */
public void append(double value){
  if (count == data.length) {
    data=PApplet.expand(data);
  }
  data[count++]=value;
}
