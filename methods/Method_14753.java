/** 
 * create a parent JSONObject named name+KEY_ARRAY.
 * @param count
 * @param page
 * @param name
 * @return {name+KEY_ARRAY : this}. if needs to be put, use {@link #putsAll(Map<? extends String, ? extends Object>)} instead
 */
public JSONRequest toArray(int count,int page,String name){
  return new JSONRequest(StringUtil.getString(name) + KEY_ARRAY,this.setCount(count).setPage(page));
}
