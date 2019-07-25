@Override public String format(String s){
  if (s == null || s.trim().isEmpty()) {
    return "";
  }
  StringBuilder sb=new StringBuilder();
  String[] authors=s.split("and");
  for (int i=0; i < authors.length; i++) {
    String[] author=authors[i].split(",");
    if (author.length < 2) {
      author=authors[i].split(" ");
      String name;
      String surname;
      if (author.length == 1) {
        sb.append(author[0].trim().toUpperCase(Locale.ROOT));
      }
 else       if (author.length == 2) {
        name=author[0].trim();
        surname=author[1].trim().toUpperCase(Locale.ROOT);
        sb.append(surname);
        sb.append(", ");
        sb.append(name);
      }
 else       if (author.length == 3) {
        name=author[0].trim();
        surname=author[1].trim().toUpperCase(Locale.ROOT) + ' ' + author[2].trim().toUpperCase(Locale.ROOT);
        sb.append(surname);
        sb.append(", ");
        sb.append(name);
      }
 else       if (author.length == 4) {
        name=author[0].trim() + ' ' + author[1].trim();
        surname=author[2].trim().toUpperCase(Locale.ROOT) + ' ' + author[3].trim().toUpperCase(Locale.ROOT);
        sb.append(surname);
        sb.append(", ");
        sb.append(name);
      }
    }
 else {
      String surname=author[0].trim().toUpperCase(Locale.ROOT);
      String name=author[1].trim();
      sb.append(surname);
      sb.append(", ");
      sb.append(name);
    }
    if (i < authors.length - 2) {
      sb.append(", ");
    }
 else     if (i == authors.length - 2) {
      sb.append(" y ");
    }
  }
  return sb.toString();
}
