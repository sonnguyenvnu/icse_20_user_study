/*
 *  Copyright 2019 http://www.hswebframework.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.hswebframework.web.controller.organizational;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hswebframework.web.NotFoundException;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.RequiresDataAccess;
import org.hswebframework.web.authorization.define.Phased;
import org.hswebframework.web.commons.entity.PagerResult;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.SimpleGenericEntityController;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.entity.organizational.PersonAuthBindEntity;
import org.hswebframework.web.entity.organizational.PersonEntity;
import org.hswebframework.web.organizational.authorization.PersonnelAuthentication;
import org.hswebframework.web.organizational.authorization.PersonnelAuthenticationHolder;
import org.hswebframework.web.service.organizational.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人员
 *
 * @author hsweb-generator-online
 */
@RestController
@RequestMapping("${hsweb.web.mappings.person:person}")
@Authorize(permission = "person", description = "人员管�?�", dataAccess = @RequiresDataAccess)
@Api(value = "人员管�?�", tags = "组织架构-人员管�?�")
public class PersonController implements SimpleGenericEntityController<PersonEntity, String, QueryParamEntity> {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public PersonService getService() {
        return personService;
    }

    @Override
    public ResponseMessage<PagerResult<PersonEntity>> list(QueryParamEntity param) {
        return SimpleGenericEntityController.super.list(param);
    }

    @GetMapping("/me")
    @ApiOperation("查看当�?登录用户的人员信�?�")
    @Authorize(merge = false)
    public ResponseMessage<PersonAuthBindEntity> getLoginUserPerson() {
        PersonnelAuthentication authorization = PersonnelAuthentication
                .current()
                .orElseThrow(NotFoundException::new);
        return getDetail(authorization.getPersonnel().getId());
    }

    @PutMapping("/me")
    @ApiOperation("修改个人信�?�")
    @Authorize(merge = false)
    public ResponseMessage<String> updateMePersonInfo(@RequestBody PersonAuthBindEntity bindEntity) {
        PersonnelAuthentication authorization = PersonnelAuthentication
                .current()
                .orElseThrow(NotFoundException::new);
        PersonAuthBindEntity old = personService
                .selectAuthBindByPk(authorization.getPersonnel().getId());

        bindEntity.setUserId(old.getUserId());
        bindEntity.setId(old.getId());
        bindEntity.setPositionIds(null);

        if (bindEntity.getPersonUser() != null) {
            bindEntity.getPersonUser().setUsername(old.getPersonUser().getUsername());
        }

        personService.updateByPk(bindEntity);
        return ResponseMessage.ok();
    }

    @GetMapping("/me/authorization")
    @ApiOperation("查看当�?登录用户的人员�?��?信�?�")
    @Authorize(merge = false)
    public ResponseMessage<PersonnelAuthentication> getLoginUserPersonDetail() {
        PersonnelAuthentication authorization = PersonnelAuthentication
                .current()
                .orElseThrow(NotFoundException::new);
        return ResponseMessage.ok(authorization);
    }

    @GetMapping("/{personId}/authorization")
    @ApiOperation("查看人员�?��?信�?�")
    @Authorize(action = Permission.ACTION_GET, dataAccess = @RequiresDataAccess(ignore = true))
    public ResponseMessage<PersonnelAuthentication> getPersonDetail(@PathVariable String personId) {
        return ResponseMessage.ok(PersonnelAuthenticationHolder.getByPersonId(personId));
    }

    @GetMapping("/{id}/detail")
    @ApiOperation("查看人员详情")
    @Authorize(action = Permission.ACTION_GET, dataAccess = @RequiresDataAccess(phased = Phased.after))
    public ResponseMessage<PersonAuthBindEntity> getDetail(@PathVariable String id) {
        return ResponseMessage.ok(personService.selectAuthBindByPk(id));
    }

    @PostMapping("/detail")
    @ApiOperation("新增人员信�?�,并关�?�用户信�?�")
    @Authorize(action = Permission.ACTION_ADD, dataAccess = @RequiresDataAccess(ignore = true))
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<String> createPersonDetail(@RequestBody PersonAuthBindEntity bindEntity) {
        return ResponseMessage.ok(personService.insert(bindEntity));
    }

    @PutMapping("/{id}/detail")
    @ApiOperation("修改人员信�?�,并关�?�用户信�?�")
    @Authorize(action = Permission.ACTION_UPDATE, dataAccess = @RequiresDataAccess(ignore = true))
    public ResponseMessage<String> getDetail(@PathVariable String id, @RequestBody PersonAuthBindEntity bindEntity) {
        bindEntity.setId(id);
        personService.updateByPk(bindEntity);
        return ResponseMessage.ok();
    }

    @GetMapping("/in-position/{positionId}")
    @ApiOperation("获�?�指定岗�?的人员")
    @Authorize(action = Permission.ACTION_GET, dataAccess = @RequiresDataAccess(phased = Phased.after))
    public ResponseMessage<List<PersonEntity>> getByPositionId(@PathVariable String positionId) {
        return ResponseMessage.ok(personService.selectByPositionId(positionId));
    }
}
