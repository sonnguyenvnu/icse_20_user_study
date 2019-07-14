public boolean onBackPressed(){
  if (currentSection != SECTION_0) {
    switchSections();
    return true;
  }
 else {
    return false;
  }
}
