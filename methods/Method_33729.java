/** 
 * ??????????????
 */
public static boolean isHandlePermission(Activity activity,String permission,String permission2){
  if (isPermission(activity,permission,permission2)) {
    return true;
  }
 else {
    int permissionCheck=ContextCompat.checkSelfPermission(activity,permission);
    int permissionCheck2=ContextCompat.checkSelfPermission(activity,permission2);
    ArrayList<String> list=new ArrayList<>();
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
      list.add(permission);
    }
    if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
      list.add(permission2);
    }
    if (list.size() > 0) {
      String[] strings=new String[list.size()];
      for (int i=0; i < list.size(); i++) {
        strings[i]=list.get(i);
      }
      ActivityCompat.requestPermissions(activity,strings,PERMISSION_CODE);
    }
    return false;
  }
}
