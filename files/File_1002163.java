package me.zhengjie.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import me.zhengjie.domain.QiniuConfig;
import me.zhengjie.domain.QiniuContent;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.repository.QiNiuConfigRepository;
import me.zhengjie.repository.QiniuContentRepository;
import me.zhengjie.service.QiNiuService;
import me.zhengjie.service.dto.QiniuQueryCriteria;
import me.zhengjie.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

/**
 * @author Zheng Jie
 * @date 2018-12-31
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class QiNiuServiceImpl implements QiNiuService {

    @Autowired
    private QiNiuConfigRepository qiNiuConfigRepository;

    @Autowired
    private QiniuContentRepository qiniuContentRepository;

    @Value("${qiniu.max-size}")
    private Long maxSize;

    private final String TYPE = "公开";

    @Override
    public Object queryAll(QiniuQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(qiniuContentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    public QiniuConfig find() {
        Optional<QiniuConfig> qiniuConfig = qiNiuConfigRepository.findById(1L);
        if(qiniuConfig.isPresent()){
            return qiniuConfig.get();
        } else {
            return new QiniuConfig();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QiniuConfig update(QiniuConfig qiniuConfig) {
        if (!(qiniuConfig.getHost().toLowerCase().startsWith("http://")||qiniuConfig.getHost().toLowerCase().startsWith("https://"))) {
            throw new BadRequestException("外链域�??必须以http://或者https://开头");
        }
        qiniuConfig.setId(1L);
        return qiNiuConfigRepository.save(qiniuConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QiniuContent upload(MultipartFile file, QiniuConfig qiniuConfig) {

        Long size = maxSize * 1024 * 1024;
        if(file.getSize() > size){
            throw new BadRequestException("文件超出规定大�?");
        }
        if(qiniuConfig.getId() == null){
            throw new BadRequestException("请先添加相应�?置，�?�?作");
        }
        /**
         * 构造一个带指定Zone对象的�?置类
         */
        Configuration cfg = QiNiuUtil.getConfiguration(qiniuConfig.getZone());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        String upToken = auth.uploadToken(qiniuConfig.getBucket());
        try {
            String key = file.getOriginalFilename();
            if(qiniuContentRepository.findByKey(key) != null) {
                key = QiNiuUtil.getKey(key);
            }
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            //解�?上传�?功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //存入数�?�库
            QiniuContent qiniuContent = new QiniuContent();
            qiniuContent.setBucket(qiniuConfig.getBucket());
            qiniuContent.setType(qiniuConfig.getType());
            qiniuContent.setKey(putRet.key);
            qiniuContent.setUrl(qiniuConfig.getHost()+"/"+putRet.key);
            qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(file.getSize()+"")));
            return qiniuContentRepository.save(qiniuContent);
        } catch (Exception e) {
           throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public QiniuContent findByContentId(Long id) {
        Optional<QiniuContent> qiniuContent = qiniuContentRepository.findById(id);
        ValidationUtil.isNull(qiniuContent,"QiniuContent", "id",id);
        return qiniuContent.get();
    }

    @Override
    public String download(QiniuContent content,QiniuConfig config){
        String finalUrl = null;
        if(TYPE.equals(content.getType())){
            finalUrl  = content.getUrl();
        } else {
            Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
            /**
             * 1�?时，�?�以自定义链接过期时间
             */
            long expireInSeconds = 3600;
            finalUrl = auth.privateDownloadUrl(content.getUrl(), expireInSeconds);
        }
        return finalUrl;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(QiniuContent content, QiniuConfig config) {
        //构造一个带指定Zone对象的�?置类
        Configuration cfg = QiNiuUtil.getConfiguration(config.getZone());
        Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(content.getBucket(), content.getKey());
            qiniuContentRepository.delete(content);
        } catch (QiniuException ex) {
            qiniuContentRepository.delete(content);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void synchronize(QiniuConfig config) {
        if(config.getId() == null){
            throw new BadRequestException("请先添加相应�?置，�?�?作");
        }
        //构造一个带指定Zone对象的�?置类
        Configuration cfg = QiNiuUtil.getConfiguration(config.getZone());
        Auth auth = Auth.create(config.getAccessKey(), config.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        //文件�??�?缀
        String prefix = "";
        //�?次迭代的长度�?制，最大1000，推�??值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共�?缀（模拟列出目录效果）。缺�?值为空字符串
        String delimiter = "";
        //列举空间文件列表
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(config.getBucket(), prefix, limit, delimiter);
        while (fileListIterator.hasNext()) {
            //处�?�获�?�的file list结果
            QiniuContent qiniuContent = null;
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                if(qiniuContentRepository.findByKey(item.key) == null){
                    qiniuContent = new QiniuContent();
                    qiniuContent.setSize(FileUtil.getSize(Integer.parseInt(item.fsize+"")));
                    qiniuContent.setKey(item.key);
                    qiniuContent.setType(config.getType());
                    qiniuContent.setBucket(config.getBucket());
                    qiniuContent.setUrl(config.getHost()+"/"+item.key);
                    qiniuContentRepository.save(qiniuContent);
                }
            }
        }

    }

    @Override
    public void deleteAll(Long[] ids, QiniuConfig config) {
        for (Long id : ids) {
            delete(findByContentId(id), config);
        }
    }
}
