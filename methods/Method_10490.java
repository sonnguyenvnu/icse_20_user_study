/** 
 * Parse an  {@link AspectRatio} from a {@link String} formatted like "4:3".
 * @param s The string representation of the aspect ratio
 * @return The aspect ratio
 * @throws IllegalArgumentException when the format is incorrect.
 */
public static AspectRatio parse(String s){
  int position=s.indexOf(':');
  if (position == -1) {
    throw new IllegalArgumentException("Malformed aspect ratio: " + s);
  }
  try {
    int x=Integer.parseInt(s.substring(0,position));
    int y=Integer.parseInt(s.substring(position + 1));
    return AspectRatio.of(x,y);
  }
 catch (  NumberFormatException e) {
    throw new IllegalArgumentException("Malformed aspect ratio: " + s,e);
  }
}
