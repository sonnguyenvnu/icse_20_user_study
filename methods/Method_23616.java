/** 
 * Add a new entry to the list.
 * @webref floatlist:method
 * @brief Add a new entry to the list
 */
public void append(float value){
  if (count == data.length) {
    data=PApplet.expand(data);
  }
  data[count++]=value;
}
