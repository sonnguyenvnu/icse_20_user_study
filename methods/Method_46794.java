public File getUsbDrive(){
  File parent=new File("/storage");
  try {
    for (    File f : parent.listFiles())     if (f.exists() && f.getName().toLowerCase().contains("usb") && f.canExecute())     return f;
  }
 catch (  Exception e) {
  }
  parent=new File("/mnt/sdcard/usbStorage");
  if (parent.exists() && parent.canExecute())   return parent;
  parent=new File("/mnt/sdcard/usb_storage");
  if (parent.exists() && parent.canExecute())   return parent;
  return null;
}
