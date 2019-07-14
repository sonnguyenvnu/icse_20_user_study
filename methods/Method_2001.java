public static LocalResourceSimpleAdapter getLazyAdapter(final Context context,@ArrayRes int arrayId){
  return new LocalResourceSimpleAdapter(context,arrayId,true);
}
