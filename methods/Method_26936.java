@SuppressLint("NewApi") @NonNull public Property<T,Integer> optimize(){
  if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
    return new android.util.IntProperty<T>(null){
      @Override public void setValue(      @NonNull T object,      int value){
        IntProperty.this.setValue(object,value);
      }
      @Override @NonNull public Integer get(      @NonNull T object){
        return IntProperty.this.get(object);
      }
    }
;
  }
 else {
    return this;
  }
}
