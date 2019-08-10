package org.forstudy.sell.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    private String wechatMpAuthorize;

    private String wechatOpenAuthorize;

    private String sell;

    private String redirectOrderList;
}
