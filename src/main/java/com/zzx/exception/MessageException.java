package com.zzx.exception;

/**
 * 用于service层向上传递消息
 */
public class MessageException extends Exception {
    public MessageException(String errorMessage) {
        super(errorMessage);
    }
}
