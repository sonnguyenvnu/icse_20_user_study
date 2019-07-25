public static String serialize(SNodeReference p){
  SNodePointer np=(SNodePointer)p;
  return np.myModelReference + "/" + StringUtil.escapeRefChars(String.valueOf(np.myNodeId));
}
