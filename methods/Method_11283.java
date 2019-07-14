private void setBitmap(final Canvas canvas,final float seatWH,final SeatState type,final float left,final float top){
  fixedThreadPool.execute(new Runnable(){
    @Override public void run(){
      Bitmap b=getSeat(seatWH,type);
      if (b != null) {
        Message msg=mHandler.obtainMessage();
        Bundle bundle=new Bundle();
        bundle.putFloat("left",left);
        bundle.putFloat("top",top);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] bitmapByte=baos.toByteArray();
        bundle.putByteArray("bitmap",bitmapByte);
        msg.setData(bundle);
        msg.obj=canvas;
        mHandler.sendMessage(msg);
      }
    }
  }
);
}
