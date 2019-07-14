public void setPalette(){
  Bitmap myBitmap=((BitmapDrawable)paletteImg.getDrawable()).getBitmap();
  ((TextView)findViewById(R.id.palette_image_title)).setText(uri.getPath().substring(uri.getPath().lastIndexOf("/") + 1));
  ((TextView)findViewById(R.id.palette_image_caption)).setText(uri.getPath());
  palette=Palette.from(myBitmap).generate();
  rvPalette=(RecyclerView)findViewById(R.id.paletteRecycler);
  rvPalette.setLayoutManager(new LinearLayoutManager(this));
  rvPalette.setNestedScrollingEnabled(false);
  paletteAdapter=new PaletteAdapter(palette.getSwatches());
  rvPalette.setAdapter(paletteAdapter);
}
