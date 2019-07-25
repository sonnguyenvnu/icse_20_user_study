public void freeze(){
  lockedCompoundPadding=new int[]{getCompoundPaddingLeft(),getCompoundPaddingRight(),getCompoundPaddingTop(),getCompoundPaddingBottom()};
  frozen=true;
}
