public void appendLn(String... chunks){
  for (  String chunk : chunks) {
    sb.append(chunk);
  }
  sb.append(cr);
}
