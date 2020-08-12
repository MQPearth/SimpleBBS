package com.zzx.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user.netdisk")
public class NetdiskConfig {
    /**
     * 1Gb的字节数
     */
    public static final long GB_1 = 1024 * 1024 * 1024;
    private Long size;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
