package org.hswebframework.web.thirdpart.ueditor;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.Context;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.ParameterParser;
import org.hswebframework.web.WebUtil;
import org.hswebframework.web.service.file.FileInfoService;
import org.hswebframework.web.service.file.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

/**
 * ueditor �?务端实现
 *
 * @see FileService
 * @since 2.0
 */
@RestController
@RequestMapping("/ueditor")
@Api(tags = "第三方应用-ueditor", value = "ueditor")
public class UeditorController {

    @Resource
    private FileService fileService;

    @Resource
    private FileInfoService resourcesService;

    @Value("${ueditor.root-path:/}")
    private String rootPath = "/";

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @PostConstruct
    public void init() {
        Context.FILE_SERVICE = fileService;
        Context.RESOURCES_SERVICE = resourcesService;
    }

    private String getDownloadPath(HttpServletRequest request) {
        return rootPath;
    }

    /**
     * ueditor上传文件
     *
     * @return 上传结果
     * @throws IOException 文件上传错误
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    @ApiOperation("上传文件")
    public String upload(@RequestParam(value = "upfile", required = false) MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = FileType.getSuffixByFilename(fileName);
        String path = fileService.saveStaticFile(file.getInputStream(), System.currentTimeMillis() + suffix);
        State state = new BaseState(true);
        state.putInfo("size", file.getSize());
        state.putInfo("title",fileName);
        state.putInfo("url", path);
        state.putInfo("type", suffix);
        state.putInfo("original",fileName);
        return state.toJSONString();
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("�?始化�?置")
    public String run(HttpServletRequest request) throws Exception {
        return new ActionEnter(request, getDownloadPath(request)).exec();
    }

}
