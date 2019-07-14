@XmlTransient public Date getExpiration(){
  if (expiresIn == null) {
    return null;
  }
  long now=System.currentTimeMillis();
  return new Date(now + (expiresIn * 1000));
}
