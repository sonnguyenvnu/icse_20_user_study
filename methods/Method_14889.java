/** 
 * @param range
 * @param id
 * @param search
 * @return
 */
public static UserListFragment createInstance(int range,long id,JSONObject search){
  UserListFragment fragment=new UserListFragment();
  Bundle bundle=new Bundle();
  bundle.putInt(ARGUMENT_RANGE,range);
  bundle.putLong(ARGUMENT_ID,id);
  bundle.putString(ARGUMENT_SEARCH,JSON.toJSONString(search));
  fragment.setArguments(bundle);
  return fragment;
}
