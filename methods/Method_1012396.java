private AlbumWrappedBean cover(SearchMusicBean.AlbumBean item){
  AlbumWrappedBean albumWrappedBean=new AlbumWrappedBean();
  albumWrappedBean.setAlbumId(item.getAlbumid());
  albumWrappedBean.setImageUrl(item.getArtistpic());
  albumWrappedBean.setTitle(item.getAlbumname());
  albumWrappedBean.setSubTitle(item.getArtistname());
  return albumWrappedBean;
}
