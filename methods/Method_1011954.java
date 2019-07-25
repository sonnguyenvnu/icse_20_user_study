private boolean equals(String prefix,String suffix,CodeStyleItem item){
  return Objects.equals(prefix,item.getPrefix()) && Objects.equals(suffix,item.getSuffix());
}
