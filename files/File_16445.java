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
package org.hswebframework.web.entity.organizational;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hswebframework.web.commons.entity.SimpleTreeSortSupportEntity;

import java.util.List;

/**
 * ç»„ç»‡
 *
 * @author hsweb-generator-online
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleOrganizationalEntity extends SimpleTreeSortSupportEntity<String> implements OrganizationalEntity {
    private static final long serialVersionUID = -1610547249282278768L;
    //å??ç§°
    private String name;
    //å…¨ç§°
    private String fullName;
    //æœºæž„ç¼–ç ?
    private String code;
    //å?¯é€‰è§’è‰²
    private java.util.List<String> optionalRoles;
    //æ˜¯å?¦å?¯ç”¨
    private Byte status;
    //å­?çº§ç»„ç»‡
    private List<OrganizationalEntity> children;

    private String areaId;


    @Override
    public String getDistrictId() {
        return areaId;
    }

    @Override
    public void setDistrictId(String districtId) {
        setAreaId(districtId);
    }
}
