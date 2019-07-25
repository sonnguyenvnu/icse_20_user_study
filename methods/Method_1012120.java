@Override public FindersOptions clone(){
  FindersOptions result=new FindersOptions();
  result.myFindersClassNames.addAll(myFindersClassNames);
  return result;
}
