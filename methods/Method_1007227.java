@Test public final void get(){
  assertTrue(personNameLens.get(oldPerson).equals(oldName));
  assertTrue(personNumberLens.get(oldPerson) == oldNumber);
  assertTrue(personStreetLens.get(oldPerson) == oldStreet);
}
