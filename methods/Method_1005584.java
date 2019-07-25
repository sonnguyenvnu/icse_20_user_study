private static HashMap<String,String> restore(){
  String list=FairyGlobal.getHostApplication().getSharedPreferences("plugins.serviceMapping",Context.MODE_PRIVATE).getString("plugins.serviceMapping.map","");
  Serializable object=null;
  if (!TextUtils.isEmpty(list)) {
    ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(Base64.decode(list,Base64.DEFAULT));
    ObjectInputStream objectInputStream=null;
    try {
      objectInputStream=new ObjectInputStream(byteArrayInputStream);
      object=(Serializable)objectInputStream.readObject();
    }
 catch (    Exception e) {
      LogUtil.printException("StubServiceMappingProcessor.restore",e);
    }
 finally {
      if (objectInputStream != null) {
        try {
          objectInputStream.close();
        }
 catch (        IOException e) {
          LogUtil.printException("StubServiceMappingProcessor.restore",e);
        }
      }
      if (byteArrayInputStream != null) {
        try {
          byteArrayInputStream.close();
        }
 catch (        IOException e) {
          LogUtil.printException("StubServiceMappingProcessor.restore",e);
        }
      }
    }
  }
  if (object != null) {
    HashMap<String,String> mapping=(HashMap<String,String>)object;
    return mapping;
  }
  return null;
}
