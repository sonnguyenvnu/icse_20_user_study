public static void join(int person,int friend){
  if (relationships[person] > relationships[friend]) {
    relationships[person]+=relationships[friend];
    people[friend]=person;
  }
 else {
    relationships[friend]+=relationships[person];
    people[person]=friend;
  }
}
