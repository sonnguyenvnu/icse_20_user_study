/** 
 * ??????? <p>?????  {@code <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>}</p> <p>?????  {@code <uses-permission android:name="android.permission.READ_CONTACTS"/>}</p>
 * @param context ???;
 * @return ?????
 */
public static List<HashMap<String,String>> getAllContactInfo(Context context){
  SystemClock.sleep(3000);
  ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
  ContentResolver resolver=context.getContentResolver();
  Uri raw_uri=Uri.parse("content://com.android.contacts/raw_contacts");
  Uri date_uri=Uri.parse("content://com.android.contacts/data");
  Cursor cursor=resolver.query(raw_uri,new String[]{"contact_id"},null,null,null);
  while (cursor.moveToNext()) {
    String contact_id=cursor.getString(0);
    if (!RxDataTool.isNullString(contact_id)) {
      Cursor c=resolver.query(date_uri,new String[]{"data1","mimetype"},"raw_contact_id=?",new String[]{contact_id},null);
      HashMap<String,String> map=new HashMap<String,String>();
      while (c.moveToNext()) {
        String data1=c.getString(0);
        String mimetype=c.getString(1);
        if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
          map.put("phone",data1);
        }
 else         if (mimetype.equals("vnd.android.cursor.item/name")) {
          map.put("name",data1);
        }
      }
      list.add(map);
      c.close();
    }
  }
  cursor.close();
  return list;
}
