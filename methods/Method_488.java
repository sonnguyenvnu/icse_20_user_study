protected LocalDate parseLocalDate(String text,String format,DateTimeFormatter formatter){
  if (formatter == null) {
    if (text.length() == 8) {
      formatter=formatter_d8;
    }
    if (text.length() == 10) {
      char c4=text.charAt(4);
      char c7=text.charAt(7);
      if (c4 == '/' && c7 == '/') {
        formatter=formatter_d10_tw;
      }
      char c0=text.charAt(0);
      char c1=text.charAt(1);
      char c2=text.charAt(2);
      char c3=text.charAt(3);
      char c5=text.charAt(5);
      if (c2 == '/' && c5 == '/') {
        int v0=(c0 - '0') * 10 + (c1 - '0');
        int v1=(c3 - '0') * 10 + (c4 - '0');
        if (v0 > 12) {
          formatter=formatter_d10_eur;
        }
 else         if (v1 > 12) {
          formatter=formatter_d10_us;
        }
 else {
          String country=Locale.getDefault().getCountry();
          if (country.equals("US")) {
            formatter=formatter_d10_us;
          }
 else           if (country.equals("BR") || country.equals("AU")) {
            formatter=formatter_d10_eur;
          }
        }
      }
 else       if (c2 == '.' && c5 == '.') {
        formatter=formatter_d10_de;
      }
 else       if (c2 == '-' && c5 == '-') {
        formatter=formatter_d10_in;
      }
    }
    if (text.length() >= 9) {
      char c4=text.charAt(4);
      if (c4 == '?') {
        formatter=formatter_d10_cn;
      }
 else       if (c4 == '?') {
        formatter=formatter_d10_kr;
      }
    }
  }
  return formatter == null ? LocalDate.parse(text) : LocalDate.parse(text,formatter);
}
