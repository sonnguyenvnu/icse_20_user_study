public String dump(){
  String[] pkgCls=parsePkgAndClsFromIntent();
  if (null != pkgCls && pkgCls.length == 2) {
    return "Package&Cls is: " + this + " " + (pkgCls[0] + " " + pkgCls[1]) + " flg=0x" + Integer.toHexString(getIntent().getFlags());
  }
 else {
    return "Package&Cls is: " + this + " flg=0x" + Integer.toHexString(getIntent().getFlags());
  }
}
