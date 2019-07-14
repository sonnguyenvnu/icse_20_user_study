/** 
 * @param idList
 * @return
 */
public static UserListFragment createInstance(List<Long> idList){
  UserListFragment fragment=new UserListFragment();
  Bundle bundle=new Bundle();
  bundle.putSerializable(ARGUMENT_ID_LIST,(Serializable)idList);
  fragment.setArguments(bundle);
  return fragment;
}
