private void update(boolean restore){
  try {
    if (restore) {
      InputStream is=getClass().getResourceAsStream("/smartim.css");
      textPane.setText(readString(is,null));
    }
 else {
      String dir=System.getProperty("idea.config.path");
      File f=new File(dir,"smartim.css");
      if (f.exists()) {
        FileInputStream fis=new FileInputStream(f);
        textPane.setText(readString(fis,null));
      }
    }
    parse();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
