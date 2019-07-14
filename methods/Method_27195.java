public static String toString(@NonNull TextInputLayout textInputLayout){
  return textInputLayout.getEditText() != null ? toString(textInputLayout.getEditText()) : "";
}
