/** 
 * @return true if number is hex or webcolor
 */
private boolean isHexColor(){
  if (handles.get(0).type == "hex" || handles.get(0).type == "webcolor") {
    int value=handles.get(0).value.intValue();
    if ((value & 0xff000000) != 0) {
      return true;
    }
  }
  return false;
}
