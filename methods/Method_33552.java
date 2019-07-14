/** 
 * Fragment??Activity?????
 */
public static void start(Fragment fragment,String type){
  Intent intent=new Intent(fragment.getActivity(),BookTypeActivity.class);
  intent.putExtra("type",type);
  fragment.startActivityForResult(intent,520);
}
