/** 
 * ???Fragment?Activity?????Fragment????
 */
public int getPosition(){
  if (position < 0) {
    argument=getArguments();
    if (argument != null) {
      position=argument.getInt(ARGUMENT_POSITION,position);
    }
  }
  return position;
}
