/** 
 * @return length 
 */
public int length(){
  int len=0;
  for (  String label : labels) {
    len+=label.length() + 1;
  }
  if (pointer != null) {
    len+=2;
  }
 else {
    len++;
  }
  return len;
}
