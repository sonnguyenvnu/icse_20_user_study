/** 
 * Returns the public URI that identifies this habit
 * @return the URI
 */
public String getUriString(){
  return String.format(Locale.US,HABIT_URI_FORMAT,getId());
}
