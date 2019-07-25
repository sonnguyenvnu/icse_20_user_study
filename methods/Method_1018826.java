/** 
 * ?Activity?dump???????
 */
public static String dump(Activity activity){
  String info="";
  if (activity instanceof InstrActivityProxy1) {
    info=((InstrActivityProxy1)activity).dump();
  }
 else {
    Intent intent=activity.getIntent();
    String[] pkgCls=parsePkgAndClsFromIntent(intent);
    if (pkgCls != null && pkgCls.length == 2) {
      info="Package&Cls is: " + activity + " " + (pkgCls[0] + " " + pkgCls[1]) + " flg=0x" + Integer.toHexString(intent.getFlags());
    }
 else {
      info="Package&Cls is: " + activity + " flg=0x" + Integer.toHexString(intent.getFlags());
    }
  }
  return info;
}
