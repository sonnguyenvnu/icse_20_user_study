@JsonIgnore public boolean isCurrentlyActive(){
  return expiresAt != null && expiresAt.after(new Date());
}
