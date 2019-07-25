private void init() throws NoInputException {
  printInitialScenario490();
  initialPurchasesOfPlayer690();
  initialShootingRanking920();
  screen.clear();
  print("<i>** Your trip is about to begin... **</i>");
  print();
  for (int j=0; j < 20; j++) {
    if (m > 2039) {
      madeIt3190(j);
      break;
    }
    print("<b>Monday, " + da[j] + ", 1847</b>. You are " + whereAreWe());
    print();
    if (f < 6) {
      print("<b>** You're low on food. Better buy some or go hunting soon. **");
      print();
    }
    if (ks == 1 || kh == 1) {
      t=t - 10;
      if (t < 0) {
        needDoctorBadly3010(j);
      }
      print("Doctor charged <b>$10</b> for his services");
      print("to treat your " + (ks == 1 ? "illness." : "injuries."));
    }
    m=(int)(m + .5);
    print("Total mileage to date is: <b>" + ((int)m) + "</b>");
    m+=200 + (a - 110) / 2.5 + 10 * rnd();
    print();
    print("Here's what you now have (no. of bullets, $ worth of other items) :");
    printInventory3350();
    question1000(j);
    eating1310(j);
    screen.clear();
    riders1390(j);
    events1800(j);
    montains2640(j);
    if (skb.hasMore()) {
      screen.clear();
    }
  }
}
