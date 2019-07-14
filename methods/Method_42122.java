private static String getHavingCluause(int excludedCount){
  if (excludedCount == 0)   return "(";
  StringBuilder res=new StringBuilder();
  res.append("HAVING (");
  res.append(MediaStore.Images.Media.DATA).append(" NOT LIKE ?");
  for (int i=1; i < excludedCount; i++)   res.append(" AND ").append(MediaStore.Images.Media.DATA).append(" NOT LIKE ?");
  return res.toString();
}
