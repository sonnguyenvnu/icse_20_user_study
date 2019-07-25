private MusicPlayBean cover(DownLoadMusicBean downLoadMusicBean){
  MusicPlayBean musicPlayBean=new MusicPlayBean();
  musicPlayBean.setIsLocal(false);
  musicPlayBean.setSongId(Long.parseLong(downLoadMusicBean.getSonginfo().getSong_id()));
  musicPlayBean.setAlbumId(Long.parseLong(downLoadMusicBean.getSonginfo().getAlbum_id()));
  musicPlayBean.setAlbumName(downLoadMusicBean.getSonginfo().getAlbum_title());
  if (!TextUtils.isEmpty(downLoadMusicBean.getSonginfo().getPic_huge())) {
    musicPlayBean.setAlbumUrl(downLoadMusicBean.getSonginfo().getPic_huge());
  }
 else   if (!TextUtils.isEmpty(downLoadMusicBean.getSonginfo().getPic_premium())) {
    musicPlayBean.setAlbumUrl(downLoadMusicBean.getSonginfo().getPic_premium());
  }
 else   if (!TextUtils.isEmpty(downLoadMusicBean.getSonginfo().getPic_big())) {
    musicPlayBean.setAlbumUrl(downLoadMusicBean.getSonginfo().getPic_big());
  }
 else   if (!TextUtils.isEmpty(downLoadMusicBean.getSonginfo().getPic_small())) {
    musicPlayBean.setAlbumUrl(downLoadMusicBean.getSonginfo().getPic_small());
  }
 else   if (!TextUtils.isEmpty(downLoadMusicBean.getSonginfo().getPic_radio())) {
    musicPlayBean.setAlbumUrl(downLoadMusicBean.getSonginfo().getPic_radio());
  }
  musicPlayBean.setArtistId(downLoadMusicBean.getSonginfo().getArtist_id());
  musicPlayBean.setArtistName(downLoadMusicBean.getSonginfo().getAuthor());
  musicPlayBean.setLrcUrl(downLoadMusicBean.getSonginfo().getLrclink());
  musicPlayBean.setSongUrl(downLoadMusicBean.getBitrate().getFile_link());
  musicPlayBean.setSongName(downLoadMusicBean.getSonginfo().getTitle());
  musicPlayBean.setTingId(downLoadMusicBean.getSonginfo().getTing_uid());
  CommonLogger.e(downLoadMusicBean.toString());
  return musicPlayBean;
}
