/** 
 * Called after else.
 */
private void gotElse(){
  tabs=s_tabs[curlyLvl][if_lev];
  p_flg[level]=sp_flg[curlyLvl][if_lev];
  ind[level]=s_ind[curlyLvl][if_lev];
  if_flg=true;
  inStatementFlag=false;
}
