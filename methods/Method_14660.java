@JsonIgnore public boolean isDirty(){
  return written == null || _modified.isAfter(written);
}
