private Person createNewRandomPerson(){
  return new Person(names[random.nextInt(names.length)],names[random.nextInt(names.length)],random.nextInt(100));
}
