private static boolean shouldUseGuavaHashCode(Context context){
  return Source.instance(context).compareTo(Source.lookup("1.7")) <= 0;
}
