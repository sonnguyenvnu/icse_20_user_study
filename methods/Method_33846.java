@BindingAdapter("android:textSelection") public static void textSelection(AppCompatEditText tv,ObservableField<String> value){
  if (!TextUtils.isEmpty(tv.getText())) {
    tv.setSelection(tv.getText().length());
  }
}
