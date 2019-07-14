@NonNull private String getDefault(@NonNull TypeName type){
  if (type.isPrimitive()) {
    if (type.equals(TypeName.BOOLEAN)) {
      return "false";
    }
 else     if (type.equals(TypeName.BYTE)) {
      return "0";
    }
 else     if (type.equals(TypeName.SHORT)) {
      return "0";
    }
 else     if (type.equals(TypeName.INT)) {
      return "0";
    }
 else     if (type.equals(TypeName.LONG)) {
      return "0L";
    }
 else     if (type.equals(TypeName.CHAR)) {
      return "\u0000";
    }
 else     if (type.equals(TypeName.FLOAT)) {
      return "0.0f";
    }
 else     if (type.equals(TypeName.DOUBLE)) {
      return "0.0d";
    }
  }
  return "null";
}
