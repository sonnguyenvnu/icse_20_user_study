/** 
 * Get a list of random Rating POJOs.
 */
public static List<Rating> getRandomList(int length){
  List<Rating> result=new ArrayList<>();
  for (int i=0; i < length; i++) {
    result.add(getRandom());
  }
  return result;
}
