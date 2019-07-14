package org.hswebframework.web.authorization.oauth2.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.oauth2.server.client.OAuth2Client;
import org.hswebframework.web.authorization.oauth2.server.client.OAuth2ClientConfigRepository;
import org.hswebframework.web.entity.oauth2.server.OAuth2ClientEntity;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oauth2-client-config")
@Api(tags = "OAuth2.0-�?务-客户端管�?�", value = "OAuth2.0-�?务-客户端管�?�")
@Authorize(permission = "oauth2-client-config", description = "OAuth2.0-�?务-客户端管�?�")
public class OAuth2ClientConfigController {

    @Autowired
    private OAuth2ClientConfigRepository repository;

    @GetMapping
    @Authorize(action = Permission.ACTION_QUERY)
    @ApiOperation("获�?�全部客户端")
    public ResponseMessage<List<OAuth2Client>> getAllClient() {
        return ResponseMessage.ok(repository.getAll());
    }


    @GetMapping("/{id}")
    @Authorize(action = Permission.ACTION_GET)
    @ApiOperation("根�?�id获�?�客户端")
    public ResponseMessage<OAuth2Client> getById(@PathVariable String id) {
        return ResponseMessage.ok(repository.getClientById(id));
    }

    @GetMapping("/owner/{userId}")
    @Authorize(action = Permission.ACTION_GET)
    @ApiOperation("根�?�绑定到用户到客户端")
    public ResponseMessage<OAuth2Client> getByOwnerId(@PathVariable String userId) {
        return ResponseMessage.ok(repository.getClientByOwnerId(userId));
    }


    @PatchMapping
    @Authorize(action = Permission.ACTION_UPDATE)
    @ApiOperation(value = "�?存客户端", notes = "如果客户端�?存在则自动新增")
    public ResponseMessage<OAuth2Client> saveOrUpdate(@RequestBody OAuth2ClientEntity clientEntity) {
        Authentication authentication = Authentication.current().orElse(null);

        if (null != authentication) {
            clientEntity.setCreatorId(authentication.getUser().getId());
        }
        clientEntity.setCreateTimeNow();

        return ResponseMessage.ok(repository.save(clientEntity));
    }

    @DeleteMapping("/{id}")
    @Authorize(action = Permission.ACTION_DELETE)
    @ApiOperation(value = "删除客户端")
    public ResponseMessage<OAuth2Client> removeById(@PathVariable String id) {
        return ResponseMessage.ok(repository.remove(id));
    }

}
