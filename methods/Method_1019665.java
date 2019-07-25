/** 
 * Append new string into annotation content.
 * @param value the value being appended
 */
public void append(String value){
  if (ignore) {
    return;
  }
  content.append(value);
}
