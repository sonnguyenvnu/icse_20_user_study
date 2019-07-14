public void openVCard(String vcard,String first_name,String last_name){
  try {
    File f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),"sharing/");
    f.mkdirs();
    f=new File(f,"vcard.vcf");
    BufferedWriter writer=new BufferedWriter(new FileWriter(f));
    writer.write(vcard);
    writer.close();
    presentFragment(new PhonebookShareActivity(null,null,f,ContactsController.formatName(first_name,last_name)));
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
