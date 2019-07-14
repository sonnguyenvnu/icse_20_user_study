package org.hswebframework.web.dev.tools.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.dev.tools.reader.FileInfo;
import org.hswebframework.web.dev.tools.writer.GeneratedCode;
import org.hswebframework.web.dev.tools.writer.CodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouhao
 * @since 3.0
 */
@RestController
@RequestMapping("/dev/tools/file")
@Authorize(permission = "dev-tools", description = "开�?�人员工具-文件管�?�")
@Api(tags = "开�?�人员工具-文件管�?�", value = "开�?�人员工具-文件管�?�")
public class FileManagerDevToolsController {

    @Autowired
    private CodeWriter codeWriter;

    @PostMapping("/write")
    @ApiOperation("写出文件")
    @Authorize(action = "write", description = "写出文件")
    public ResponseMessage<String> write(@RequestBody List<GeneratedCode> codes) {
        return ResponseMessage.ok(codeWriter.write(codes));
    }

    @GetMapping("/list")
    @ApiOperation("获�?�目录下的全部文件,�?包�?��?目录")
    @Authorize(action = "read", description = "读�?�文件")
    public ResponseMessage<List<FileInfo>> write(@RequestParam String path) {
        File file = new File(path);
        if (!file.exists()) {
            return ResponseMessage.error(404,"文件�?存在");
        }
        List<FileInfo> list;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                list = new java.util.ArrayList<>();
            } else {
                list = Stream.of(files)
                        .map(FileInfo::from)
                        .collect(Collectors.toList());
            }
        } else {
            list = Collections.singletonList(FileInfo.from(file));
        }
        return ResponseMessage.ok(list);
    }

    @GetMapping("/read")
    @ApiOperation("读�?�文本文件内容")
    @SneakyThrows
    @Authorize(action = "read", description = "读�?�文件")
    public ResponseMessage<String> read(@RequestParam String file) {
        File fileInfo = new File(file);
        if (!fileInfo.exists()) {
            return ResponseMessage.error(404,"文件�?存在");
        }
        if (fileInfo.length() > 2 * 1024 * 1024 * 1024L) {
            return ResponseMessage.error(500, "文件过大,无法读�?�");
        }
        List<String> list = Files.readAllLines(Paths.get(file));
        StringJoiner joiner = new StringJoiner("\n");
        list.forEach(joiner::add);
        return ResponseMessage.ok(joiner.toString());
    }

}
