public static void fuzz(Random r,Address a){
  a.setCity(CITY_NAMES[r.nextInt(CITY_NAMES.length)]);
  a.setStreet(r.nextInt(4096) + " " + STREET_NAMES[r.nextInt(STREET_NAMES.length)]);
  a.setState(STATE_NAMES[r.nextInt(STATE_NAMES.length)]);
  StringBuilder zip=new StringBuilder();
  zip.append(String.format("%05d",r.nextInt(99999)));
  if (r.nextBoolean()) {
    zip.append(String.format("-%04d",r.nextInt(9999)));
  }
  a.setZip(zip.toString());
}
