@Override public void initEvent(){
  findViewById(R.id.llAboutUpdate).setOnClickListener(this);
  findViewById(R.id.llAboutShare).setOnClickListener(this);
  findViewById(R.id.llAboutComment).setOnClickListener(this);
  findViewById(R.id.llAboutDeveloper,this).setOnLongClickListener(this);
  findViewById(R.id.llAboutWeibo,this).setOnLongClickListener(this);
  findViewById(R.id.llAboutContactUs,this).setOnLongClickListener(this);
}
