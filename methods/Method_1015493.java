protected static String dump(String[] names,long[] values,long[] versions){
  StringBuilder sb=new StringBuilder();
  if (names != null) {
    for (int i=0; i < names.length; i++) {
      sb.append(names[i]).append(": ").append(values[i]).append(" (").append(versions[i]).append(")\n");
    }
  }
  return sb.toString();
}
