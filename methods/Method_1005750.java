public static String aidl(AndroidTarget target,String aidlDir){
  return target.getName() + "_" + aidlDir.replaceAll("[/-]","_") + "_aidls";
}
