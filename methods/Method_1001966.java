public CString duplicate(){
  return new CString(new ArrayList<Character>(this.data2),currentStart);
}
