/** 
 * Add a new entry to the list.
 * @webref intlist:method
 * @brief Add a new entry to the list
 */
public void append(int value){
  if (count == data.length) {
    data=PApplet.expand(data);
  }
  data[count++]=value;
}
