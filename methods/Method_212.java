public static int find(int person){
  if (people[person] != person) {
    people[person]=find(people[person]);
  }
  return people[person];
}
