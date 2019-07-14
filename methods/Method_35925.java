public String getBase64Body(){
  return body.isBinary() ? body.asBase64() : null;
}
