public static int union(int person,int friend){
  person=find(person);
  friend=find(friend);
  if (person != friend) {
    if (relationships[person] > relationships[friend]) {
      relationships[person]+=relationships[friend];
      people[friend]=person;
      return relationships[person];
    }
 else {
      relationships[friend]+=relationships[person];
      people[person]=friend;
      return relationships[friend];
    }
  }
  return relationships[person];
}
