public String valueString(int offset){
  try {
    for (int a=offset; a < data.length; a++) {
      if (data[a] == '\0') {
        if (offset == a - offset) {
          return "";
        }
        return new String(data,offset,a - offset);
      }
    }
    return "";
  }
 catch (  Exception e) {
    e.printStackTrace();
    return "";
  }
}
