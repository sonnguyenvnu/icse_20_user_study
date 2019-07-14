/** 
 * ??App???????
 * @param info ????
 * @return ??
 */
public static Intent getShareInfoIntent(String info){
  Intent intent=new Intent(Intent.ACTION_SEND);
  intent.setType("text/plain");
  return intent.putExtra(Intent.EXTRA_TEXT,info);
}
