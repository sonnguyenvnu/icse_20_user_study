/** 
 * Navigate a JSON tree (made up of Maps and Lists) to "lookup" the value at a particular path. Example : given Json Object json = { "a" : { "b" : [ "x", "y", "z" ] } } navigate( json, "a", "b", 0 ) will return "x". It will traverse down the nested "a" and return the zeroth item of the "b" array. You will either get your data, or null. It should never throw an Exception; even if - you ask to index an array with a negative number - you ask to index an array wiht a number bigger than the array size - you ask to index a map that does not exist - your input data has objects in it other than Map, List, String, Number.
 * @param source the source JSON object (Map, List, String, Number)
 * @param paths varargs path you want to travel
 * @return the object of Type <T> at final destination
 */
public static <T>T navigate(final Object source,final Object... paths){
  Object destination=source;
  for (  Object path : paths) {
    if (path == null || destination == null) {
      return null;
    }
    if (destination instanceof Map) {
      destination=((Map)destination).get(path);
    }
 else     if (destination instanceof List) {
      if (!(path instanceof Integer)) {
        return null;
      }
      List destList=(List)destination;
      int pathInt=(Integer)path;
      if (pathInt < 0 || pathInt >= destList.size()) {
        return null;
      }
      destination=destList.get(pathInt);
    }
 else {
      return null;
    }
  }
  return cast(destination);
}
