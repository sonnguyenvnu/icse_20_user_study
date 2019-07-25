public String encoding(){
  return String.format("%s=%s",URLEncodingUtil.encoding(getName(),Consts.UTF_8,true),URLEncodingUtil.encoding(getValue(),Consts.UTF_8,true));
}
