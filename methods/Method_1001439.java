/** 
 * Maps the specified tic value onto a text label in accordance with the map description.
 */
public String form(double ticValue){
  String result="?";
  for (int i=0; i < _map.length; i++) {
    if (_map[i].isInside(ticValue)) {
      result=_map[i].label;
      break;
    }
  }
  return result;
}
