protected @NonNull Environment environment(){
  return ((KSApplication)getContext().getApplicationContext()).component().environment();
}
