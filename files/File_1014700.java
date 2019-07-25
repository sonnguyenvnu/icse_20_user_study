package com.cg.baseproject.utils.android.image;

import android.content.Context;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.widget.ImageView;

import com.cg.baseproject.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * @Description:主�?功能:Picasso图片加载工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月19日 15:47
 * @Copyright: 个人版�?�所有
 * @Company:
 * @version: 1.0.0
 */

/**
 * picasso 图片处�?�工具类<BR/>
 * 优缺点：对图片处�?�强大，�?�消已�?�?在视野范围的ImageView图片资�?的加载（�?�则会导致图片错�?），
 * 使用4.0+系统上的HTTP缓存�?�代替�?盘缓存；�?�能得到结果，�?能监�?�图片下载过程
 * <BR/> Picasso �?�以与okhttp�?��?
 *
 * 如果使用Picasso�?�时也使用了okhttp库，那么项目�?行的时候�?�能会报出一下异常：
 Picasso detected an unsupported OkHttp on the classpath
 针对该情况，网上说需�?引入：compile 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
 �?��?�时引入一下三个包：
 compile 'com.squareup.okhttp:okhttp:2.4.0'
 compile 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
 compile 'com.squareup.picasso:picasso:2.4.0'

 */
public class PicassoUtils {
    private static PicassoUtils instance;
    /**圆形*/
    public static String PICASSO_BITMAP_SHOW_CIRCLE_TYPE="PicassoUtils_Circle_Type";
    /**圆角*/
    public static String PICASSO_BITMAP_SHOW_ROUND_TYPE="PicassoUtils_Round_Type";
    /**正常*/
    public static String PICASSO_BITMAP_SHOW_NORMAL_TYPE="PicassoUtils_Normal_Type";
    public static PicassoUtils getinstance(){
        if(instance==null){
            synchronized (PicassoUtils.class) {
                if(instance==null){
                    instance=new PicassoUtils();
                }
            }
        }
        return instance;
    }
    //Picasso使用的方法汇总：
    //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
    //Picasso.with(context).load(url).into(view);
    //Picasso.with(context).load(url) .resize(50, 50).centerCrop().into(imageView)
    ////这里的placeholder将resource传入通过getResource.getDrawable�?�资�?，所以�?�以是张图片也�?�以是color id
    //Picasso.with(context).load(url).placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder_error).into(imageView);
    //
    // Resources, assets, files, content providers 加载图片都支�?
    //Picasso.with(context).load(R.drawable.landing_screen).into(imageView1);
    //Picasso.with(context).load("file:///android_asset/DvpvklR.png").into(imageView2);
    //Picasso.with(context).load(new File(...)).into(imageView3);
    ////这里显示notification的图片
    //Picasso.with(activity).load(Data.URLS[new Random().nextInt(Data.URLS.length)]).resizeDimen(R.dimen.notification_icon_width_height,    R.dimen.notification_icon_width_height).into(remoteViews, R.id.photo, NOTIFICATION_ID, notification);
    ////这里是通过设置tag标签，就是当�?传过�?�的context，这样就�?�以根�?�这个context tag�?�pause和resume显示了
    //Picasso.with(context).load(url).placeholder(R.drawable.placeholder).error(R.drawable.error).fit().tag(context).into(view);
    ////监�?�onScrollStateChanged的时候调用执行
    //picasso.resumeTag(context);
    //picasso.pauseTag(context);
    //Picasso.with(context).load(contactUri).placeholder(R.drawable.contact_picture_placeholder).tag(context).into(holder.icon);
    /**
     * 加载图片通过地�?�
     * @param context
     * @param path
     *  <BR/>
     *  String imagePath = "/mnt/sdcard/phone_pregnancy/header.png";  <BR/>
        String imagefileUrl = Scheme.FILE.wrap(imagePath); <BR/>
        //图片�?��?于Content provider
        String contentprividerUrl = "content://media/external/audio/albumart/13";   <BR/>
        //图片�?��?于assets
        //  String assetsUrl = Scheme.ASSETS.wrap("image.png");  <BR/>
        String assetsUrl = "assets://fail_image.9.png";  <BR/>
        //图片�?��?于  drawable
        //  String drawableUrl = Scheme.DRAWABLE.wrap("R.drawable.ic_launcher.png");<BR/>
        String drawableUrl = "drawable://" + R.drawable.ic_add; <BR/>
        //图片�?��?于  网络
        String neturi = "http://ww2.sinaimg.cn/large/49aaa343jw1dgwd0qvb4pj.jpg";<BR/>
        <P>
     * @param imageView
     * @param placeholderimage  �?��?图片
     * @param errorimage  加载错误图片
     * @param bitmapShowType   PICASSO_BITMAP_SHOW_CIRCLE_TYPE ， PICASSO_BITMAP_SHOW_ROUND_TYPE ，PICASSO_BITMAP_SHOW_NORMAL_TYPE
     * @param roundRadius  设置圆角�?�径
     */
    public void LoadImage(Context context,String path,ImageView imageView,int placeholderimage,int errorimage,String bitmapShowType,float roundRadius){
        if(bitmapShowType.equals(PICASSO_BITMAP_SHOW_CIRCLE_TYPE)){
            Picasso.with(context).load(path).placeholder(placeholderimage).error(errorimage).transform(new CircleTransform()).into(imageView);
        }else if(bitmapShowType.equals(PICASSO_BITMAP_SHOW_ROUND_TYPE)){
            Picasso.with(context).load(path).placeholder(placeholderimage).error(errorimage).transform(new RoundTransform(roundRadius)).into(imageView);
        }else {
            Picasso.with(context).load(path).placeholder(placeholderimage).error(errorimage).into(imageView);
        }
    }
    /**
     * 加载图片本地 通过id
     * @param context
     * @param localimage  R.drawable.landing_screen
     * @param imageView
     * @param bitmapShowType   PICASSO_BITMAP_SHOW_CIRCLE_TYPE ， PICASSO_BITMAP_SHOW_ROUND_TYPE ，PICASSO_BITMAP_SHOW_NORMAL_TYPE
     * @param roundRadius  设置圆角�?�径
     */
    public void LoadImage(Context context,int localimage,ImageView imageView,String bitmapShowType,float roundRadius){
        if(bitmapShowType.equals(PICASSO_BITMAP_SHOW_CIRCLE_TYPE)){
            Picasso.with(context).load(localimage).placeholder(R.drawable.img_loading).error(R.drawable.img_load_error).transform(new CircleTransform()).into(imageView);
        }else if(bitmapShowType.equals(PICASSO_BITMAP_SHOW_ROUND_TYPE)){
            Picasso.with(context).load(localimage).transform(new RoundTransform(roundRadius)).into(imageView);
        }else {
            Picasso.with(context).load(localimage).into(imageView);
        }
    }
    /**
     * 加载图片 设置宽高  图片默认居中 （centerCrop() ）
     * @param context
     * @param path
     * @param imageView
     * @param targetWidth
     * @param targetHeight
     * @param bitmapShowType   PICASSO_BITMAP_SHOW_CIRCLE_TYPE ， PICASSO_BITMAP_SHOW_ROUND_TYPE ，PICASSO_BITMAP_SHOW_NORMAL_TYPE
     * @param roundRadius  设置圆角�?�径
     */
    public void LoadImageWithWidtAndHeight(Context context,String path,ImageView imageView,int targetWidth,int targetHeight,String bitmapShowType,float roundRadius){
        if(bitmapShowType.equals(PICASSO_BITMAP_SHOW_CIRCLE_TYPE)){
            Picasso.with(context).load(path).resize(targetWidth, targetHeight).centerCrop().transform(new CircleTransform()).into(imageView);
        }else if(bitmapShowType.equals(PICASSO_BITMAP_SHOW_ROUND_TYPE)){
            Picasso.with(context).load(path).resize(targetWidth, targetHeight).centerCrop().transform(new RoundTransform(roundRadius)).into(imageView);
        }else {
            Picasso.with(context).load(path).resize(targetWidth, targetHeight).centerCrop().into(imageView);
        }
    }
    //--------------------------------------------------
    /**
     *设置圆形头�?
     */
    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
    //------------------------------------------------------
    /**
     * 绘制圆角
     */
    public class RoundTransform implements Transformation{
        private float radius;
        public RoundTransform(float radius) {
            this.radius=radius;
        }
        @Override
        public String key() {
            return "round";
        }

        @Override
        public Bitmap transform(Bitmap bitmap) {
            int size = Math.min(bitmap.getWidth(), bitmap.getHeight());

            int x = (bitmap.getWidth() - size) / 2;
            int y = (bitmap.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(bitmap, x, y, size, size);
            if (squaredBitmap != bitmap) {
                bitmap.recycle();
            }
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, radius, radius, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            squaredBitmap.recycle();
            return output;
        }

    }
}
