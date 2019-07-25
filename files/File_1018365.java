package com.pixivic.util;

import com.pixivic.model.DefaultDownloadHttpResponse;
import com.pixivic.model.DefaultUploadHttpResponse;
import com.pixivic.model.illust.ImageUrls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.gm4java.engine.GMException;
import org.gm4java.engine.GMServiceException;
import org.gm4java.engine.support.PooledGMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
final public class ImageUtil {
    private final HttpClient httpClient;
    private final HttpUtil httpUtil;
    private final ZipUtil zipUtil;
    private final PooledGMService pooledGMService;
    private final DefaultDownloadHttpResponse defaultDownloadHttpResponse;
    private final DefaultUploadHttpResponse defaultUploadHttpResponse;
    private HashMap<String, String> formData;
    @Setter
    private String cookie;
    @Setter
    @Getter
    @Value("${imageSave.path}")
    private String path;
    @Value("${sina.username}")
    private String username;
    @Value("${sina.password}")
    private String password;
    @Value("${imgur.client_id}")
    private String imgur_client_id;
    @Value("${postimage.token}")
    private String postimage_token;
    @Value("${postimage.upload_session}")
    private String postimage_upload_session;

    //常�?区
    private final static Long MAXSIZE_LEVEL = 10485760L;//大于10m压缩

    private final static String SINAURL = "http://picupload.weibo.com/interface/pic_upload.php?s=xml&ori=1&data=1&rotate=0&wm=&app=miniblog&mime=image%2Fjpeg";
    private final static String SINA301URL = "https://wx4.sinaimg.cn/large/007iuyE8gy1g18b8poxhlj30rs12n7wh.jpg";

    private final static ImageUrls IMAGEURLS301 = new ImageUrls(SINA301URL, SINA301URL);

    private final static String SINAORIGINAL_PRE = "https://ws4.sinaimg.cn/large/";
    private final static String SINALARGE_PRE = "https://ws4.sinaimg.cn/mw690/";

    private final static String UPLOADCC_URL = "https://upload.cc/image_upload";
    private final static String UPLOADCC_PRE = "https://upload.cc/";
    private final static String UPLOADCC_FILENAME = "uploaded_file[]";
    private final static String UPLOADCC_ERRO = "上传到uploadCC失败";
    private final static String UPLOADCC_1 = "url";
    private final static String UPLOADCC_2 = "\"url\":\"";
    private final static String UPLOADCC_3 = "\",\"thumbnail\":\"";

    private final static String IMGBB_URL = "https://zh-cn.imgbb.com/json";
    private final static String IMGBB_PRE = "https://i0.wp.com/";
    private final static String IMGBB_TYPE = "type";
    private final static String IMGBB_FILE = "file";
    private final static String IMGBB_ACTION = "action";
    private final static String IMGBB_UPLOAD = "upload";
    private final static String IMGBB_SOURCE = "source";
    private final static String IMGBB_1 = "\"url\":\"https:\\/\\/";
    private final static String IMGBB_2 = "\",\"size_formatted";
    private final static String IMGBB_3 = "\"medium\"";
    private final static String IMGBB_4 = "\",\"size\"";
    private final static String IMGBB_ERRO = "上传到ImgBB失败";

    private final static String POSTIMG_URL = "https://postimages.org/json/rr";
    private final static String POSTIMG_TOKEN = "token";
    private final static String POSTIMG_UPLOAD_SESSION = "upload_session";
    private final static String POSTIMG_NUMFILES = "numfiles";
    private final static String POSTIMG_FILE = "file";
    private final static String POSTIMG_1 = "main-image\" src=\"";
    private final static String POSTIMG_2 = "\" style=\"display:block;";

    private final static String COOKIE = "Cookie";
    private final static String REFERER = "Referer";
    private final static String BOUNDARY = "******";
    private final static String CONTENT_TYPE = "Content-Type";
    private final static String CONTENT_LENGTH = "Content-Length";
    private final static String UA = "User-Agent";
    private final static String CHROME_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36";
    private final static String MULTIPART = "multipart/form-data, boundary=******";

    private final static String PIXIV_REFERER = "https://app-api.pixiv.net/";
    private final static String SINA_REFERER = "https://weibo.com";

    private final static String PID = "<pid>";
    private final static String PID_END = "</pid>";

    private final static String JPG = ".jpg";
    private final static String PNG = ".png";
    private final static String GIF = ".gif";
    private final static String ZIP = ".zip";

    private final static String GIF_TYPE = "ugoira";
    private final static String GIF_PRE = "https://i.pximg.net/img-zip-ugoira/img/";
    private final static String GIF_POS = "600x600.zip";
    private final static String IMG = "/img/";

    private final static String GM_1 = "convert -quality ";
    private final static String GM_2 = "% -limit threads 4 -limit memory 256MB ";
    private final static String GM_3 = " ";
    private final static String GM_4 = "convert -loop 0 -delay 10 -limit threads 4 -limit memory 256MB ";
    private final static String GM_5 = "/*.jpg ";

    private final static String ERROR = "上传失败";
    private final static String URL_DEAL = "\\";
    private final static String NONE = "";

    @PostConstruct
    public void init() throws IOException, InterruptedException {
        formData = new HashMap<>() {{
            put("entry", "sso");
            put("gateway", "1");
            put("from", "null");
            put("savestate", "30");
            put("useticket", "0");
            put("pagerefer", "");
            put("vsnf", "1");
            put("su", Base64.getEncoder().encodeToString(username.getBytes()));
            put("service", "sso");
            put("sp", password);
            put("sr", "1920*1080");
            put("encoding", "UTF-8");
            put("cdult", "3");
            put("domain", "sina.com.cn");
            put("prelt", "0");
            put("returntype", "TEXT");
        }};
        setSinaCookies();
    }

    public CompletableFuture<ImageUrls> deal(String url, String fileName, Integer sanity_level, String type) {
        if (GIF_TYPE.equals(type)) {
            url = GIF_PRE + url.substring(url.indexOf(IMG) + 5, url.length() - 5) + GIF_POS;
        }
        return download(url, fileName, sanity_level, type).whenComplete((resp, throwable) -> {
            Integer respSize = Integer.valueOf(resp.headers().firstValue(CONTENT_LENGTH).get());
            if (respSize > MAXSIZE_LEVEL) {//使用�?应头看大�?
                System.out.println(fileName + " 尺寸过大准备进行压缩---------------"); //压缩(png百分99转jpg,其他则百分80转jpg)
                int quality = resp.body().endsWith(PNG) ? (respSize > MAXSIZE_LEVEL * 2 ? 70 : 99) : 80;
                try {
                    pooledGMService.execute(GM_1 + quality + GM_2 + resp.body().toString() + GM_3 + Paths.get(path, fileName) + JPG);
                } catch (IOException | GMException | GMServiceException e) {
                    System.err.println("图片处�?�异常");
                }
                System.out.println(fileName + "压缩完毕--------------------------------------------");
            }
            if (type.equals("ugoira")) {  //动图通�?�
                System.out.println(fileName + " 检测为动�?图片,准备下载解压ZIP并�?�并为GIF文件");
                //解压+�?�并gif
                try {
                    Path path = Paths.get(this.path, fileName);
                    zipUtil.unzip(path, resp.body().toString());
                    String s = path.toString();
                    pooledGMService.execute(GM_4 + s + GM_5 + s + GIF);
                    System.out.println(fileName + "�?��?GIF�?功,等待上传----------------------------");
                } catch (IOException | GMException | GMServiceException e) {
                    System.err.println("图片处�?�异常");
                }
            }
        }).thenApply(HttpResponse::body).thenCompose(body -> {
                    try {
                        if (GIF_TYPE.equals(type)) {  //动图通�?�
                            Path path = Paths.get(this.path, fileName + GIF);
                            return Files.size(path) < MAXSIZE_LEVEL ? uploadToImgBB(path) : uploadToSina(Paths.get(this.path, fileName, "000000.jpg"));//gif大�?�?制
                        } else {
                            return sanity_level < 4 ? uploadToSina(body) : balanceUpload(body);
                        }
                    } catch (IOException e) {
                        return CompletableFuture.completedFuture(IMAGEURLS301);//上传异常
                    }
                }
        );
    }

    @Scheduled(cron = "0 0 */3 * * ?")
    public void setSinaCookies() throws IOException, InterruptedException {
        HttpRequest oauth = HttpRequest.newBuilder()
                .uri(URI.create("https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.15)&_=1403138799543"))
                .header(CONTENT_TYPE, "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(httpUtil.getPostEntity(formData)))
                .build();
        String responseCookie = httpClient.send(oauth, HttpResponse.BodyHandlers.ofString()).headers().map().get("set-cookie").get(1);
        setCookie(responseCookie);
    }

    public CompletableFuture<ImageUrls> uploadToSina(Path path) throws IOException {
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create(SINAURL))
                .header(COOKIE, this.cookie)
                .header(UA, CHROME_UA)
                .POST(HttpRequest.BodyPublishers.ofFile(path))
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String body = response.body();
                    if (response.statusCode() == 200 && body.contains(PID)) {
                        String pid = body.substring(body.indexOf(PID) + 5, body.indexOf(PID_END));
                        return new ImageUrls(SINAORIGINAL_PRE + pid + JPG, SINALARGE_PRE + pid + JPG);
                    }
                    return IMAGEURLS301;//301图片等待扫�??�?��?上传uploadcc
                });
    }

    public CompletableFuture<ImageUrls> uploadToUploadCC(Path path) {
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .setBoundary(BOUNDARY)
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody(UPLOADCC_FILENAME, path.toFile(), ContentType.IMAGE_PNG, path.getFileName().toString())
                .build();
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create(UPLOADCC_URL))
                .header(REFERER, UPLOADCC_PRE)
                .header(UA, CHROME_UA)
                .header(CONTENT_TYPE, MULTIPART)
                .POST(HttpRequest.BodyPublishers.ofByteArray(entityToByteArray(httpEntity)))
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String body = response.body();
                    if (response.statusCode() == 200 && body.contains(UPLOADCC_1)) {
                        String url = UPLOADCC_PRE + body.substring(body.indexOf(UPLOADCC_2) + 7, body.indexOf(UPLOADCC_3)).replace(URL_DEAL, NONE);
                        return new ImageUrls(url, url);
                    }
                    System.err.println(path + UPLOADCC_ERRO);
                    return new ImageUrls(ERROR + path, NONE);
                });
    }

    public CompletableFuture<ImageUrls> uploadToImgBB(Path path) {
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addTextBody(IMGBB_TYPE, IMGBB_FILE)
                .addTextBody(IMGBB_ACTION, IMGBB_UPLOAD)
                .setBoundary(BOUNDARY)
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody(IMGBB_SOURCE, path.toFile(), ContentType.IMAGE_GIF, path.getFileName().toString())
                .build();
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create(IMGBB_URL))
                .header(REFERER, IMGBB_URL)
                .header(CONTENT_TYPE, MULTIPART)
                .header(UA, CHROME_UA)
                .POST(HttpRequest.BodyPublishers.ofByteArray(entityToByteArray(httpEntity)))
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String body = response.body();
                    if (response.statusCode() == 200) {
                        String originalUrl = IMGBB_PRE + body.substring(body.indexOf(IMGBB_1) + 17, body.indexOf(IMGBB_2)).replace(URL_DEAL, NONE);
                        String largeUrl = IMGBB_PRE + body.substring(body.indexOf(IMGBB_1, body.indexOf(IMGBB_3)) + 17, body.indexOf(IMGBB_4, body.indexOf(IMGBB_3))).replace(URL_DEAL, NONE);
                        if (originalUrl.endsWith(GIF))
                            originalUrl = originalUrl.replace(IMGBB_PRE, "https://");
                        return new ImageUrls(originalUrl, largeUrl);
                    }
                    System.err.println(path + IMGBB_ERRO);
                    return IMAGEURLS301;
                });
    }

    public CompletableFuture<ImageUrls> uploadToPostimage(Path path) {
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addTextBody(POSTIMG_TOKEN, postimage_token)
                .addTextBody(POSTIMG_UPLOAD_SESSION, postimage_upload_session)
                .addTextBody(POSTIMG_NUMFILES, "1")
                .setBoundary(BOUNDARY)
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody(POSTIMG_FILE, path.toFile(), ContentType.IMAGE_GIF, path.getFileName().toString())
                .build();
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create(POSTIMG_URL))
                .header(UA, CHROME_UA)
                .header(CONTENT_TYPE, MULTIPART)
                .POST(HttpRequest.BodyPublishers.ofByteArray(entityToByteArray(httpEntity)))
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String body = response.body();
                    if (response.statusCode() == 200 && body.contains(UPLOADCC_1)) {
                        return body.substring(body.indexOf(UPLOADCC_2) + 7, body.indexOf("\\/", body.length() - 15)).replace(URL_DEAL, NONE);
                    }
                    System.err.println(path + "上传到Postimage失败");
                    return "上传失败" + path;
                }).thenCompose(this::getPostimageOriginalUrl);
    }

    private CompletableFuture<ImageUrls> getPostimageOriginalUrl(String url) {
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(UA, CHROME_UA)
                .GET()
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String body = response.body();
                    if (response.statusCode() == 200 && body.contains("download")) {
                        return new ImageUrls(
                                IMGBB_PRE + body.substring(body.indexOf("<a href=\"https://i.postimg.cc/") + 17, body.indexOf("\" id=\"download\"") - 5),
                                IMGBB_PRE + body.substring(body.indexOf(POSTIMG_1) + 25, body.indexOf(POSTIMG_2)).replace(URL_DEAL, NONE));
                    }
                    System.err.println(path + "获�?�PostImage原图链接失败");
                    return new ImageUrls(ERROR + path, NONE);
                });
    }

    public CompletableFuture<Boolean> scanUrl(String url) {
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(UA, CHROME_UA)
                .header(REFERER, SINA_REFERER)
                .GET()
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.discarding())
                .thenApply(HttpResponse::statusCode)
                .thenApply(status -> !status.equals(200)).completeOnTimeout(false, 1, TimeUnit.MINUTES);
    }

    public CompletableFuture<ImageUrls> balanceUpload(String filename) {
        long id = Thread.currentThread().getId() & 3;
        return id > 1 ? uploadToUploadCC(Paths.get(path, filename)) : (id == 0 ? uploadToImgBB(Paths.get(path, filename)) : uploadToPostimage(Paths.get(path, filename)));
    }

    public CompletableFuture<ImageUrls> balanceUpload(Path path) {
        long id = Thread.currentThread().getId() & 3;
        return id > 1 ? uploadToUploadCC(path) : (id == 0 ? uploadToImgBB(path) : uploadToPostimage(path));
    }

    public CompletableFuture<HttpResponse<Path>> download(String url, String fileName, Integer sanity_level, String type) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(REFERER, PIXIV_REFERER)
                .header(UA, CHROME_UA)
                .GET()
                .build();
        String fullFileName = sanity_level > 5 ? fileName + url.substring(url.length() - 4) : (GIF_TYPE.equals(type) ? fileName + ZIP : fileName + JPG);
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofFile(Paths.get(path, fullFileName))).completeOnTimeout(defaultDownloadHttpResponse, 8, TimeUnit.MINUTES);
    }

    private byte[] entityToByteArray(HttpEntity httpEntity) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            httpEntity.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    private CompletableFuture<String> uploadToImgur(Path path) throws IOException {
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create("https://api.imgur.com/3/image"))
                .header("Authorization", imgur_client_id)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .POST(HttpRequest.BodyPublishers.ofFile(path))
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String s = response.body();
                    if (response.statusCode() == 200 && s.contains("link")) {
                        return s.substring(s.indexOf("\"link\":\"") + 8, s.indexOf("\",\"mp4\"")).replace("\\", "");
                    }
                    System.err.println(path + "上传到imgur失败");
                    return "上传失败" + path;
                });
    }

    public void uploadToVim_cn(Path path) throws IOException, InterruptedException {
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                .addBinaryBody("image", path.toFile(), ContentType.IMAGE_PNG, path.getFileName().toString())
                .setBoundary("******")
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .build();
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create("https://img.vim-cn.com/"))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .header("Content-Type", "multipart/form-data, boundary=******")
                .POST(HttpRequest.BodyPublishers.ofByteArray(entityToByteArray(httpEntity)))
                .build();
        System.out.println(httpClient.send(upload, HttpResponse.BodyHandlers.ofString()).body());
    }

    public CompletableFuture<String> uploadToSMMS(Path path) {
        MultipartEntityBuilder smfile = MultipartEntityBuilder.create()
                .addBinaryBody("smfile", path.toFile(), ContentType.IMAGE_PNG, path.getFileName().toString())
                .setBoundary("******")
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        HttpEntity httpEntity = smfile
                .build();
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create("https://sm.ms/api/upload"))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .header("Content-Type", "multipart/form-data, boundary=******")
                .POST(HttpRequest.BodyPublishers.ofByteArray(entityToByteArray(httpEntity)))
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String body = response.body();
                    if (response.statusCode() == 200 && body.contains("url")) {
                        return body.substring(body.indexOf("\"url\":\"") + 7, body.indexOf("\",\"delete\"")).replace("\\", "");
                    }
                    System.err.println(path + "上传到SMMS失败");
                    return "上传失败" + path;
                });
    }

        /*public CompletableFuture<String> uploadToPostimage2(Path path) throws IOException {
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create("http://api.postimage.org/1/upload"))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(URLEncoder.encode(Base64.getEncoder().encodeToString(Files.readAllBytes(path)), Charset.defaultCharset())))
                .build();
        return httpClient.sendAsync(upload, HttpResponse.BodyHandlers.ofString()).completeOnTimeout(defaultUploadHttpResponse, 4, TimeUnit.MINUTES)
                .thenApply(response -> {
                    String body = response.body();
                    System.out.println(body);
                    if (response.statusCode() == 200 && body.contains("<page>")) {
                        return body.substring(body.indexOf("<page>") + 6, body.indexOf("</page>")).replace("http", "https");
                    }
                    System.err.println(path + "上传到Postimage失败");
                    return "上传失败" + path;
                }).thenCompose(this::getPostimageOriginalUrl);
    }*/
}
