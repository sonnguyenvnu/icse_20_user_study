public static IocValue convert(String value,boolean dftRefer){
  IocValue iocValue=new IocValue();
  if (dftRefer && value.startsWith(":")) {
    iocValue.setType(IocValue.TYPE_NORMAL);
    iocValue.setValue(value.substring(1));
  }
 else   if (value.contains(":")) {
    String type=value.substring(0,value.indexOf(':'));
    if (IocValue.types.contains(type)) {
      iocValue.setType(type);
      if (value.endsWith(":")) {
        iocValue.setValue("");
      }
 else       iocValue.setValue(value.substring(value.indexOf(':') + 1));
    }
 else {
      iocValue.setType(IocValue.TYPE_NORMAL);
      iocValue.setValue(value);
    }
  }
 else {
    iocValue.setType(IocValue.TYPE_REFER);
    iocValue.setValue(value);
  }
  return iocValue;
}
